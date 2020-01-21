import * as moment from 'moment/moment';
import { DateTime } from 'luxon';

//TODO: cannot stay like this!
const dateTimeUtil = {

    getTimeZone: function () {
        return 'Australia/Sydney';
        //TODO: eliminate this
    },

    //Returns DateTime from 2020-01-18T03:03Z. Assumes ZULU
    createDateTimeFromString : function(text) {
        let parsedVal = text.substring(0, 10) + ' ' + text.substring(11, 16);
        return DateTime.fromFormat(parsedVal, 'yyyy-LL-dd HH:mm', {'zone': 'UTC'});
    },

    //Returns expiry date 'MMM DD'
    createExpDateFormatted : function() {
        let dt = DateTime.local();
        console.debug("BEFORE >> " + dt.toFormat('LLL dd') + " >> " + dt.zoneName);
        return dt.toFormat('LLL dd');
    },

    convertFromOffsetZuluToExpDateDisplay: function(item) {
        const outputFormat = 'MMM DD';

        return moment(item)
            .subtract(moment().utcOffset(), 'm')
            .format(outputFormat);
    },

    //Returns NOW formatted: 2018-12-25T10:00:00Z to '25-Dec-2018'
    createTodayDateFormatted: function() {
        let dt = DateTime.local();

        console.debug("BEFORE >> " + dt.toFormat('dd-LLL-yyyy HH:mm a Z') + " >> " + dt.zoneName);
        return dt.toFormat('dd-LLL-yyyy');
    },

    //Converts Java Date from 2020-01-18T02:33Z to DD-MMM-YYYY
    convertFromOffsetZuluToDisplay: function(text) {
        // console.debug(parsedVal);
        let dt = this.createDateTimeFromString(text).toLocal();
        // console.debug(dt.toLocal().toString());
        return dt.toFormat('dd-LLL-yyyy');
    },

    //converts 2018-10-17 21:00:00.000000 +11:00 => 2018-10-17T10:00:00Z into ...Nov17'18
    convertExpiryDateForDisplay: function(text) {
        let dt = this.createDateTimeFromString(text).toLocal();
        return dt.toFormat('LLL dd');
    },

    //Converts Local Time to java format OffsetDateTime
    //BEFORE >> 18-Jan-2020 13:33 PM +11 >> Australia/Sydney
    //AFTER  >> 18-Jan-2020 02:33 AM +0 >> UTC
    //GWAPI  >> 2020-01-18T02:33:00Z >> Java Format
    //Assuming the input is in Local time!!!
    convertDateToOffsetDateTime: function(text) {
        let now = DateTime.local();
        let dt = DateTime.fromFormat(text, 'dd-LLL-yyyy')
            .plus({
            'hours' : now.hour,
            'minutes' : now.minute
        }).toUTC();

        console.debug("AFTER  >> " + dt.toFormat('dd-LLL-yyyy HH:mm a Z') + " >> " + dt.zoneName);
        return dt.toFormat('yyyy-LL-dd') + 'T' + dt.toFormat('HH:mm:ss') + 'Z';
    },

    //Converts 'MMM DD to OffsetZulu example: 2018-12-25T10:00:00Z
    convertExpToOffsetDateTime: function(text) {
        let now = DateTime.local();
        let dt = DateTime.fromFormat(text, 'LLL dd').toUTC();
        console.debug("AFTER  >> " + dt.toFormat('dd-LLL-yyyy HH:mm a Z') + " >> " + dt.zoneName);
        return dt.toFormat('yyyy-LL-dd') + 'T' + dt.toFormat('HH:mm:ss') + 'Z';
    },

    /*
        VALIDATIONS
     */

    checkIfDateIsCorrect: function(text) {
        let obj = DateTime.fromFormat(text, 'dd-LLL-yyyy');
        return obj !== null && obj.isValid === true;
    },

    checkIfExpDateIsCorrect: function(text) {
        let obj = DateTime.fromFormat(text, 'LLL dd');
        return obj !== null && obj.isValid === true;
    },

    //sort dates. Compares 2 DateTime Objects
    sortDates: function (a, b) {
        const dateA = this.createDateTimeFromString(a);
        const dateB = this.createDateTimeFromString(b);
        return dateA > dateB;
    }
};

export default dateTimeUtil;

