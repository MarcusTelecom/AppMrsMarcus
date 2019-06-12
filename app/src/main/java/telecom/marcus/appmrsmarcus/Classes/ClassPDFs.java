package telecom.marcus.appmrsmarcus.Classes;

public class ClassPDFs {
    private String id_pdf;
    private String title;
    private String description;
    private String insert_date;
    private String url;

    public ClassPDFs(String id_pdf, String title, String descrition, String date, String url) {
        this.id_pdf = id_pdf;
        this.title = title;
        this.description = descrition;
        this.insert_date = date;
        this.url = url;
    }

    public String getId_pdf() {
        return id_pdf;
    }

    public void setId_pdf(String id_pdf) {
        this.id_pdf = id_pdf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
