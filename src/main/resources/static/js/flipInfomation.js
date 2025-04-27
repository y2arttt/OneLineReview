document.addEventListener("DOMContentLoaded", () => {
    const desc = document.getElementById("description");
    const toggleBtn = document.getElementById("toggleButton");

    if (desc && toggleBtn) {
        toggleBtn.addEventListener("click", () => {
            const isExpanded = desc.classList.toggle("full-text");
            toggleBtn.textContent = isExpanded ? "접기" : "더보기";
        });
    } else {
        console.error("Could not find description or toggleButton elements");
    }
});