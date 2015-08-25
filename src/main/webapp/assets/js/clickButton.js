/**
 * Created by qqy on 15/8/25.
 */
$(function() {
    $('#tel-btn').on('click', function () {
        $('#ii-tel').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#text-tel').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#gender-btn').on('click', function () {
        $('#ii-gender').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#text-gender').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#age-btn').on('click', function () {
        $('#ii-age').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#text-age').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#height-btn').on('click', function () {
        $('#ii-height').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#text-height').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });

    $('#weight-btn').on('click', function () {
        $('#ii-weight').modal({
            relatedTarget: this,
            onConfirm: function (e) {
                $('#text-weight').html(e.data);
            },
            onCancel: function (e) {
            }
        });
    });
});

