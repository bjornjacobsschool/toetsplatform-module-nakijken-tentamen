package nl.han.toetsplatform.module.nakijken.model;

import java.sql.Time;
import java.util.Date;

public class Tentamen_Moment {
    private String tentamenMomentId;
    private Date tentamenMomentDatum;
    private Time tentamenMomentBegintijd;
    private Time tentamenMomentEindtijd;
    private String tentamenMomentLokalen;
    private String tentamenMomentSleutel;
    private String tentamenMomentToegestaneHulpmiddelen;
    private String tentamenMomentBijzonderheden;

    public String getTentamenMomentId() {
        return tentamenMomentId;
    }

    public void setTentamenMomentId(String tentamenMomentId) {
        this.tentamenMomentId = tentamenMomentId;
    }

    public Date getTentamenMomentDatum() {
        return tentamenMomentDatum;
    }

    public void setTentamenMomentDatum(Date tentamenMomentDatum) {
        this.tentamenMomentDatum = tentamenMomentDatum;
    }

    public Time getTentamenMomentBegintijd() {
        return tentamenMomentBegintijd;
    }

    public void setTentamenMomentBegintijd(Time tentamenMomentBegintijd) {
        this.tentamenMomentBegintijd = tentamenMomentBegintijd;
    }

    public Time getTentamenMomentEindtijd() {
        return tentamenMomentEindtijd;
    }

    public void setTentamenMomentEindtijd(Time tentamenMomentEindtijd) {
        this.tentamenMomentEindtijd = tentamenMomentEindtijd;
    }

    public String getTentamenMomentLokalen() {
        return tentamenMomentLokalen;
    }

    public void setTentamenMomentLokalen(String tentamenMomentLokalen) {
        this.tentamenMomentLokalen = tentamenMomentLokalen;
    }

    public String getTentamenMomentSleutel() {
        return tentamenMomentSleutel;
    }

    public void setTentamenMomentSleutel(String tentamenMomentSleutel) {
        this.tentamenMomentSleutel = tentamenMomentSleutel;
    }

    public String getTentamenMomentToegestaneHulpmiddelen() {
        return tentamenMomentToegestaneHulpmiddelen;
    }

    public void setTentamenMomentToegestaneHulpmiddelen(String tentamenMomentToegestaneHulpmiddelen) {
        this.tentamenMomentToegestaneHulpmiddelen = tentamenMomentToegestaneHulpmiddelen;
    }

    public String getTentamenMomentBijzonderheden() {
        return tentamenMomentBijzonderheden;
    }

    public void setTentamenMomentBijzonderheden(String tentamenMomentBijzonderheden) {
        this.tentamenMomentBijzonderheden = tentamenMomentBijzonderheden;
    }
}
