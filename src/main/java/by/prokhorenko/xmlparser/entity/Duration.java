package by.prokhorenko.xmlparser.entity;

import javax.xml.datatype.XMLGregorianCalendar;


public class  Duration {
    private XMLGregorianCalendar startDate;
    private XMLGregorianCalendar endDate;

    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    public void setStartDate(XMLGregorianCalendar startDate) {
        this.startDate = startDate;
    }

    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(XMLGregorianCalendar endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Duration duration = (Duration) o;

        if (startDate != null ? !startDate.equals(duration.startDate) : duration.startDate != null) return false;
        return endDate != null ? endDate.equals(duration.endDate) : duration.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Duration{");
        sb.append("startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }
}
