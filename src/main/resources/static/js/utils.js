function dateFormatter(value) {
    var date = new Date(value);
    var currentDate = new Date();
    var seconds = Math.floor((currentDate - date) / 1000);
    var intervals = Math.floor(seconds / 2592000);
    if (intervals >= 1) {
        return date.toLocaleString();
    }
    intervals = Math.floor(seconds / 604800);
    if (intervals >= 1) {
        return intervals + " tuần trước";
    }
    intervals = Math.floor(seconds / 86400);
    if (intervals >= 1) {
        return intervals + " ngày trước";
    }
    intervals = Math.floor(seconds / 3600);
    if (intervals >= 1) {
        return intervals + " tiếng trước";
    }
    intervals = Math.floor(seconds / 60);
    if (intervals >= 1) {
        return intervals + " phút trước";
    }
    return "vừa nãy";
}