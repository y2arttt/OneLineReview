document.addEventListener("DOMContentLoaded", () => {
    const scrollerWrap = document.getElementById("scrollerWrap");
    if (!scrollerWrap) {
        return;
    }

    const reviewGrid = scrollerWrap.querySelector('.review-grid');
    if (!reviewGrid) {
        return;
    }

    // 센티널 엘리먼트 생성 (스크롤 감지용)
    const sentinel = document.createElement('div');
    sentinel.id = 'sentinel';
    sentinel.style.height = '1px';
    sentinel.style.width = '100%';

    scrollerWrap.parentNode.insertBefore(sentinel, scrollerWrap.nextSibling);

    const observerOptions = {
        root: null,
        rootMargin: '0px 0px 200px 0px',
        threshold: 0.1
    };

    let page = 1;
    let isLoading = false;
    let hasMoreData = true;

    const fetchMoreData = async () => {
        if (isLoading || !hasMoreData) return;
        isLoading = true;



        let loaderCol = document.getElementById('loaderCol');
        if (!loaderCol) {
            loaderCol = document.createElement('div');
            loaderCol.id = 'loaderCol';
            loaderCol.classList.add('d-flex', 'justify-content-center', 'py-3', 'w-100');

            const loader = document.createElement('div');
            loader.classList.add('spinner-border', 'text-primary');
            loader.innerHTML = '<span class="visually-hidden">로딩 중...</span>';

            loaderCol.appendChild(loader);

            reviewGrid.parentNode.insertBefore(loaderCol, reviewGrid.nextSibling);
        }

        try {

            const url = `/review/list?page=${page}&size=10`;
            const response = await fetch(url);


            const html = await response.text();


            // HTML 파싱
            const tempDiv = document.createElement('div');
            tempDiv.innerHTML = html;


            let newCards = [];

            // 1. 먼저 카드 직접 찾기
            const directCards = tempDiv.querySelectorAll('.card');
            if (directCards.length > 0) {

                newCards = directCards;
            }
            // 2. review-grid 내부의 div 찾기
            else {
                const newReviewGrid = tempDiv.querySelector('.review-grid');
                if (newReviewGrid) {
                     const gridChildren = newReviewGrid.children;
                    if (gridChildren.length > 0) {
                          newCards = Array.from(gridChildren);
                    }
                }
                // 3. 마지막으로 첫 번째 자식 요소에서 카드 찾기 시도
                else if (tempDiv.firstElementChild) {
                    const innerGrid = tempDiv.firstElementChild.querySelector('.review-grid');
                    if (innerGrid) {
                         newCards = Array.from(innerGrid.children);
                    }
                }
            }

            // 새 카드가 없으면 더 이상 데이터가 없는 것으로 판단
            if (newCards.length === 0) {
                hasMoreData = false;
                observer.unobserve(sentinel);
                return;
            }


            // 새 카드를 리뷰 그리드에 추가
            newCards.forEach(card => {
                // 카드가 이미 .card 클래스를 가진 경우
                if (card.classList && card.classList.contains('card')) {
                    const wrapperDiv = document.createElement('div');
                    wrapperDiv.appendChild(card.cloneNode(true));
                    reviewGrid.appendChild(wrapperDiv);
                }
                // 카드가 .card를 감싸는 div인 경우
                else {
                    reviewGrid.appendChild(card.cloneNode(true));
                }
            });

            // 다음 페이지로 증가
            page++;

        } catch (error) {
            console.error("데이터 로딩 실패:", error);
        } finally {
            isLoading = false;
            const loader = document.getElementById('loaderCol');
            if (loader) loader.remove();
        }
    };

    const onIntersect = (entries, observer) => {
        entries.forEach(entry => {
            if (entry.isIntersecting && hasMoreData) {
                fetchMoreData();
            }
        });
    };

    const observer = new IntersectionObserver(onIntersect, observerOptions);
    observer.observe(sentinel);

});