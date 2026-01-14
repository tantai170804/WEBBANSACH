<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="toast-container position-fixed bottom-0 end-0 p-3" style="z-index: 1100">
    <div id="appToast" class="toast border-0" role="alert" aria-live="assertive">
        <div class="d-flex">
            <div class="toast-body" id="toastMessage">
                <!-- message -->
            </div>
            <button type="button"
                    class="btn-close me-2 m-auto"
                    data-bs-dismiss="toast"
                    aria-label="Close">
            </button>
        </div>
    </div>
</div>

<script>
function showToast(message, type = 'success') {
    const toastEl = document.getElementById('appToast');
    const toastBody = document.getElementById('toastMessage');

    toastBody.innerText = message;

    toastEl.className = 'toast border-0 ' +
        (type === 'error' ? 'text-bg-danger' : 'text-bg-success');

    const toast = new bootstrap.Toast(toastEl, { delay: 2500 });
    toast.show();
}
</script>
