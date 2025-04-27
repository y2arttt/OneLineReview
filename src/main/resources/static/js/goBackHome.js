const goBackOrHome = () => {
    if (document.referrer) {
        history.back();
    } else {
        window.location.assign('/');
    }
};