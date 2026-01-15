<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Upload hình ảnh</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<style>
.preview-img {
            height: 260px;
            object-fit: contain;
            padding: 10px;
}
</style>
</head>
<body>

<div class="container mt-5">
    <h3 class="mb-4">Upload hình ảnh</h3>

    <form action="${pageContext.request.contextPath}/admin/image/upload"
          method="post"
          enctype="multipart/form-data">

        <div class="mb-3">
            <label class="form-label">Chọn hình ảnh</label>
            <input type="file"
                   name="image"
                   class="form-control"
                   accept="image/*"
                   onchange="previewImage(event)"
                   required>
        </div>

        <img id="preview" class="preview-img mb-3">

        <div>
            <button class="btn btn-primary">Upload</button>
        </div>
    </form>
</div>

<script>
function previewImage(event) {
    const img = document.getElementById("preview");
    img.src = URL.createObjectURL(event.target.files[0]);
    img.style.display = "block";
}
</script>

</body>
</html>
