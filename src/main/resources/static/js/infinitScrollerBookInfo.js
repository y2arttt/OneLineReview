document.addEventListener("DOMContentLoaded", () => {
    const reviewListContainer = document.querySelector(".review-list-container");

    // 리뷰 목록 컨테이너가 없으면 실행하지 않음
    if (!reviewListContainer) {
        return;
    }


    // 감지 대상 요소 생성
    const sentinel = document.createElement('div');
    sentinel.id = 'reviewSentinel';
    sentinel.style.height = '1px';
    sentinel.style.width = '100%';

    // 리뷰 목록 컨테이너 뒤에 감지 요소 추가
    reviewListContainer.parentNode.insertBefore(sentinel, reviewListContainer.nextSibling);

    const options = {
        root: null,
        rootMargin: '0px 0px 200px 0px',
        threshold: 0.1,
    };

    let page = 1;
    let isLoading = false;
    let hasMoreData = true;

    // URL에서 ISBN 가져오기
    const pathParts = window.location.pathname.split('/');
    const isbn = pathParts[pathParts.length - 1];



    const fetchMoreReviews = async () => {
        try {
            if (isLoading || !hasMoreData) return;
            isLoading = true;


            // 로딩 표시기 추가
            let loaderEl = document.getElementById('reviewLoader');
            if (!loaderEl) {
                loaderEl = document.createElement('div');
                loaderEl.id = 'reviewLoader';
                loaderEl.classList.add('d-flex', 'justify-content-center', 'py-3');

                const spinner = document.createElement('div');
                spinner.classList.add('spinner-border', 'text-primary');
                spinner.innerHTML = '<span class="visually-hidden">로딩 중...</span>';

                loaderEl.appendChild(spinner);
                reviewListContainer.appendChild(loaderEl);
            }

            // 리뷰 데이터 가져오기
            const url = `/review/bookInfo/${isbn}?page=${page}&size=5`;


            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const html = await response.text();

            // 응답 처리
            const tempDiv = document.createElement('div');
            tempDiv.innerHTML = html;

            // 리뷰 카드들 선택 - bookCard 클래스를 사용
            const newReviews = tempDiv.querySelectorAll('.card.shadow-sm.mb-3');


            // 더 이상 불러올 리뷰가 없으면 무한 스크롤 중단
            if (newReviews.length === 0) {
                hasMoreData = false;
                observer.unobserve(sentinel);

                return;
            }

            // 새 리뷰를 컨테이너에 추가
            newReviews.forEach(review => {
                reviewListContainer.appendChild(review.cloneNode(true));
            });

            // 다음 페이지 준비
            page++;

        } catch (error) {
            console.error("리뷰 로딩 중 오류 발생:", error);
        } finally {
            isLoading = false;

            // 로딩 표시기 제거
            const loader = document.getElementById('reviewLoader');
            if (loader) loader.remove();
        }
    };

    // Intersection Observer 설정
    const onIntersect = (entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting && hasMoreData) {
                fetchMoreReviews();
            }
        });
    };

    const observer = new IntersectionObserver(onIntersect, options);
    observer.observe(sentinel);
});