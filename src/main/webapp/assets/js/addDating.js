/**
 * Created by 倩钰 on 2015/8/24.
 */
$(function() {
    $('#title-btn').on('click', function() {
        $('#ad-title').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#title-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#location-btn').on('click', function() {
        $('#ad-location').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#location-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#time-btn').on('click', function() {
        $('#ad-time').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#time-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#partner-btn').on('click', function() {
        $('#ad-partner').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#partner-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });

    $('#age-btn').on('click', function() {
        $('#ad-age').modal({
            relatedTarget: this,
            onConfirm: function(e) {
                $('#age-text').html(e.data);
            },
            onCancel: function(e) {
            }
        });
    });
});
