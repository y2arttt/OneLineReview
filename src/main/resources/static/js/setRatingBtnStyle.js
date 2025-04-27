const setRating = (value, index) => {
    document.getElementById('ratingInput').value = value;
    resetActiveButtons();
    const selectedButton = document.querySelector(`.rating-btn[value="${value}"]`);
    if (selectedButton) {
        selectedButton.classList.remove('btn-outline-primary');
        selectedButton.classList.add('btn-primary');
        selectedButton.classList.add(`rating-color-${index}`);
    }
};

const resetActiveButtons = () => {
    const buttons = document.querySelectorAll('.rating-btn');
    buttons.forEach((btn, i) => {
        btn.classList.remove('btn-primary');
        btn.classList.add('btn-outline-primary');
        for (let j = 1; j <= 10; j++) {
            btn.classList.remove(`rating-color-${j}`);
        }
    });
};

document.addEventListener('DOMContentLoaded', () => {
    const ratingBtns = document.querySelectorAll('.rating-btn');
    ratingBtns.forEach(btn => {
        btn.addEventListener('click', (e) => {
            const value = e.currentTarget.value;
            const index = e.currentTarget.dataset.index;
            setRating(value, index);
        });
    });
});
