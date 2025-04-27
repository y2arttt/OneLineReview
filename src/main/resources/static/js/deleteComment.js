document.addEventListener('DOMContentLoaded', () => {
    document.body.addEventListener('click', async (e) => {
        const btn = e.target.closest('.delete-comment-btn');
        if (!btn) return;

        e.preventDefault();

        const id = btn.dataset.id;
        const reviewId = btn.dataset.reviewId;

        try {
            btn.disabled = true;
            btn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>';

            const formData = new FormData();
            formData.append('id', id);
            formData.append('reviewId', reviewId);

            const response = await fetch('/comment/delete', {
                method: 'POST',
                body: new URLSearchParams(formData)
            });
            if (!response.ok) {
                throw new Error(`서버 오류: ${response.status}`);
            }

            const commentItem = btn.closest('.comment-item');

            setTimeout(() => {
                commentItem.remove();

                // 댓글 카운트 업데이트
                const commentCount = document.querySelector('.comment-count');
                if (commentCount) {
                    const currentCount = parseInt(commentCount.textContent) - 1;
                    commentCount.textContent = currentCount;
                }
            }, 300);

        } catch (error) {
            btn.disabled = false;
            btn.innerHTML = 'X';

            const errorMsg = document.createElement('div');
            errorMsg.className = 'alert alert-danger mt-2 mb-2 p-2 small';
            errorMsg.textContent = '댓글을 삭제하는 중 오류가 발생했습니다. 다시 시도해주세요.';

            const commentActions = btn.closest('.comment-actions');
            commentActions.appendChild(errorMsg);

            // 3초 후 오류 메시지 제거
            setTimeout(() => {
                errorMsg.remove();
            }, 3000);
        }
    });
});