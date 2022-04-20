<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>suwaTestUploadImage</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <style>
        /* // 給拖曳區畫個框線 */
        #dragArea {
            width: 200px;
            border-style: dashed;
            border-width: 0.2em;
            height: 200px;
        }

        h4 {
            margin: 20px auto 10px;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
	<form action="ProductController" method="post" enctype="multipart/form-data">
        <div>
            prodId <input type="text" name="prodId" />
        </div>
        <div>
            stock <input type="text" name="stock" />
        </div>
        <div>
            price<input type="text" name="price" />
        </div>
        <div>
            prodName<input type="text" name="prodName" />
        </div>
        <div>
            description<input type="text" name="descript" />
        </div>
        <div>
            category<input type="text" name="category" />
        </div>
        <div>
            petkind<input type="text" name="petkind" />
        </div>
        <input type="file" name="file" siez="50" id="uploadImage" accept="image/*"/>
         <br>
        <input type="hidden" id="base64Str" name="base64Str" />
        <input type="hidden" name="toDo" value="create"/>
    	   <input type = "submit" value = "upload" />
    </form>
    <h4>Or drop image here:</h4>
    <div id="dragArea"></div>
    <h4>Preview:</h4>
    <div id="previewDiv"></div>
    	
    <script type="text/javascript">
    function previewFiles(files) {
        if (files && files.length >= 1) {
            $.map(files, file => {
                convertFile(file)
                    .then(data => {
                        //console.log(data) // 把編碼後的字串輸出到console
                        $("#base64Str").val(data)
                        console.log($("#base64Str").val().length)
                        showPreviewImage(data, file.name)
                    })
                    .catch(err => console.log(err))
            })
        }
    }

    // 使用FileReader讀取檔案，並且回傳Base64編碼後的source
    function convertFile(file) {
        return new Promise((resolve, reject) => {
            // 建立FileReader物件
            let reader = new FileReader()
            // 註冊onload事件，取得result則resolve (會是一個Base64字串)
            reader.onload = () => { resolve(reader.result) }
            // 註冊onerror事件，若發生error則reject
            reader.onerror = () => { reject(reader.error) }
            // 讀取檔案
            reader.readAsDataURL(file)
        })
    }

    // 在頁面上新增<img>
    function showPreviewImage(src, fileName) {
        let image = new Image(250) // 設定寬250px
        image.name = fileName
        image.src = src // <img>中src屬性除了接url外也可以直接接Base64字串
        $("#previewDiv").append(image).append(`<p>File: ${image.name}`)
    }

    // 當上傳檔案改變時清除目前預覽圖，並且呼叫previewFiles()
    $("#uploadImage").change(function () {
        $("#previewDiv").empty() // 清空當下預覽
        previewFiles(this.files) // this即為<input>元素
    })

    // dragover事件：拖曳(滑鼠還沒鬆開)至放置區時，就要先防止瀏覽器進行開圖
    $("#dragArea").on("dragover", function (e) {
        e.preventDefault()
    })

    // 當拖曳區發生drop事件時，接受格式為image的資料
    $("#dragArea").on("drop", function (e) {
        // 防止事件傳遞及預設事件發生
        e.stopPropagation()
        e.preventDefault()
        // 取得data type為image/*的資料
        e.originalEvent.dataTransfer.getData("image/*")
        let files = e.originalEvent.dataTransfer.files
        console.dir(files) // 看看File長什麼樣子
        $("#previewDiv").empty() // 清空當下預覽
        previewFiles(files)
    })
    </script>
</body>
</html>