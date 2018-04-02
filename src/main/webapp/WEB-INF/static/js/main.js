function update_active_class() {
    $("ul.nav > li").click(function (e) {
        e.preventDefault();
        $("ul.nav > li").removeClass("active");
        $(this).addClass("active");
    })
}

function load_page(obj,event) {
    event.preventDefault();
    var obj = $(obj);
    var obj_href = obj.attr("href");
    var menuContainer = $("#menu-container");
    menuContainer.load(obj_href);
    update_active_class();
}