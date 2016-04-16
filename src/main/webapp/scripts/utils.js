'use strict';

if (!String.prototype.trim) {
  (function() {
    // Make sure we trim BOM and NBSP
    var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
    String.prototype.trim = function() {
      return this.replace(rtrim, "");
    }
  })();
}

function int2Weekday(n) {
  var weekdays = [ 'Error', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
      'Friday', 'Saturday', 'Sunday' ];
  return (n >= 1 && n <= 7) ? weekdays[n] : weekdays[0];
}