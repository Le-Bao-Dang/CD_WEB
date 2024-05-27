
package org.uaf.cd_web.components;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Format {
    public Format() {
    }

    public String formatDateTimeNow(LocalDateTime date) {
        int var10000 = date.getYear();
        return "" + var10000 + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + " " + date.getHour() + ":" + date.getMinute() + ":" + date.getSecond();
    }

    public String formatDateTime(int i) {
        List<Integer> thang31Ngay = new ArrayList();
        List<Integer> thang30Ngay = new ArrayList();
        thang31Ngay.add(1);
        thang31Ngay.add(3);
        thang31Ngay.add(5);
        thang31Ngay.add(7);
        thang31Ngay.add(8);
        thang31Ngay.add(10);
        thang31Ngay.add(12);
        thang30Ngay.add(4);
        thang30Ngay.add(6);
        thang30Ngay.add(9);
        thang30Ngay.add(11);
        LocalDateTime date = LocalDateTime.now();
        int ngay = date.getDayOfMonth();
        int thang = date.getMonthValue();
        int nam = date.getYear();
        if (thang31Ngay.contains(thang)) {
            if (ngay + i > 31) {
                ngay = i - (31 - ngay);
                if (thang + 1 > 12) {
                    thang = 1;
                    ++nam;
                } else {
                    ++thang;
                }
            } else {
                ngay += i;
            }
        } else if (thang30Ngay.contains(thang)) {
            if (ngay + i > 30) {
                ngay = i - (31 - ngay);
                if (thang + 1 > 12) {
                    thang = 1;
                    ++nam;
                } else {
                    ++thang;
                }
            } else {
                ngay += i;
            }
        } else if (thang == 2 && this.isNamNhuan(nam)) {
            if (ngay + i > 29) {
                ngay = i - (29 - ngay);
                if (thang + 1 > 12) {
                    thang = 1;
                    ++nam;
                } else {
                    ++thang;
                }
            } else {
                ngay += i;
            }
        } else if (ngay + i > 28) {
            ngay = i - (28 - ngay);
            if (thang + 1 > 12) {
                thang = 1;
                ++nam;
            } else {
                ++thang;
            }
        } else {
            ngay += i;
        }

        return "" + nam + "-" + thang + "-" + ngay;
    }

    public boolean isNamNhuan(int year) {
        boolean namNhuan = false;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    namNhuan = true;
                }
            } else {
                namNhuan = true;
            }
        }

        return namNhuan;
    }
}
