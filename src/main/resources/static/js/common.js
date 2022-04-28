// function ajaxPost(url, data) {
//
//     let returnData = ''
//     $.ajax({
//         url: url,
//         data: data,
//         type: "POST",
//         beforeSend : function(xhr)
//         {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
//             xhr.setRequestHeader(header, token);
//         },
//         success: function (res) {
//             callback(res)
//         },
//         error : function(res){
//
//         }
//     })
// }

const ajaxCall = {
    getCall: function (url, dataType, successCallBack) {
        $.ajax({
            url: url,
            type: 'GET',
            dataType: dataType,
            beforeSend: function (xhr) {
                //csrf 토큰 추가시 활성화 필요
                //xhr.setRequestHeader(token_header, token);
            },
            success: function (response) {
                if (!cmmnUtil.isEmpty(successCallBack)) {
                    successCallBack(response);
                }
            }
        });
    },
    postCall: function (url, data, dataType, successCallBack) {
        $.ajax({
            url: url,
            type: 'POST',
            data : data,
            dataType: dataType,
            beforeSend: function (xhr) {
                {   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                }
            },
            success: function (response) {
                if (!cmmnUtil.isEmpty(successCallBack)) {
                    successCallBack(response);
                }
            }
        });
    }
}

const cmmnUtil = {
    isEmpty: function (value) {
        if (value == "" || value == null || value == undefined) {
            return true;
        } else {
            return false;
        }
    }
}


