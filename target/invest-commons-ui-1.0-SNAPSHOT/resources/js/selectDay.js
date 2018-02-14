function selectDay_init() {
    var selectDay_yy = document.getElementById("selectDay_yy");
    var selectDay_mm = document.getElementById("selectDay_mm");
    var selectDay_dd = document.getElementById("selectDay_dd");
    var y = new Date().getFullYear();
    var i;
    for (i=(y-5); i<=y; i++) {
        selectDay_yy.options.add(new Option(i,i));
    }
    for (i=1; i<=12; i++) {
    var MM = i>9 ? i :'0'+i;
        selectDay_mm.options.add(new Option(MM, MM));
    }
    for (i=1; i<=31; i++) {
    var DD = i>9 ? i :'0'+i;
        selectDay_dd.options.add(new Option(DD, DD));
    }
}
function selectDay_YMChange() {
    var yy = document.getElementById("selectDay_yy").value;
    var mm = document.getElementById("selectDay_mm").value;
    if (yy=='' || mm=='') {
        selectDay_Change();
        return;      
    }
    var intyy = parseInt(yy);
    var intmm = parseInt(mm);
    var daysInMonth = 32 - new Date(intyy, intmm-1, 32).getDate();
    var dayCombo = document.getElementById("selectDay_dd");
    var dayOption = dayCombo.options;
    var idx;
    if (daysInMonth < (dayOption.length-1)) {
        for (idx=dayOption.length-1; idx>daysInMonth; idx--)
            dayCombo.remove(idx);
    } else if (daysInMonth > (dayOption.length-1)) {
        for (idx=dayOption.length; idx<=daysInMonth; idx++) {
            var DD = idx>9 ? idx :'0'+idx;
            dayOption.add(new Option(DD, DD));
        }
    }
    selectDay_Change();
}
function selectDay_Change() {
    if(typeof window.selectDayOnChange == 'function') {
        selectDayOnChange();
    }
}
function selectDay_Set(ymd) {
    if (ymd.length==8) {
        // yyyyMMdd
        jQuery('#selectDay_yy').val(ymd.substr(0,4));
        jQuery('#selectDay_mm').val(ymd.substr(4,2));
        jQuery('#selectDay_dd').val(ymd.substr(6,2));
    } else if (ymd.length==10) {
        // yyyy/MM/dd
        jQuery('#selectDay_yy').val(ymd.substr(0,4));
        jQuery('#selectDay_mm').val(ymd.substr(5,2));
        jQuery('#selectDay_dd').val(ymd.substr(8,2));
    }
}
