var main = {
    init : function () {
        var _this = this;
        document.getElementById('btn-save')?.addEventListener('click', function () {
            _this.save();
        });

        document.getElementById('btn-update')?.addEventListener('click', function () {
            _this.update();
        });

        document.getElementById('btn-delete')?.addEventListener('click', function () {
            _this.delete();
        })
    },

    save : function () {
        var data = {
            title: document.getElementById('title').value,
            author: document.getElementById('author').value,
            content: document.getElementById('content').value,
        };
        
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update : function () {
        var data = {
            title: document.getElementById('title').value,
            content: document.getElementById('content').value,
        };

        var id = document.getElementById('id').value;

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = document.getElementById('id').value;

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();