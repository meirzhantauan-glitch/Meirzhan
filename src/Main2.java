// Report объектісі
class Report {
    private String header;
    private String content;
    private String footer;

    public void setHeader(String header) { this.header = header; }
    public void setContent(String content) { this.content = content; }
    public void setFooter(String footer) { this.footer = footer; }

    public void show() {
        System.out.println(header);
        System.out.println(content);
        System.out.println(footer);
    }
}

// Builder интерфейсі
interface IReportBuilder {
    void setHeader(String header);
    void setContent(String content);
    void setFooter(String footer);
    Report getReport();
}

// Text Report Builder
class TextReportBuilder implements IReportBuilder {
    private Report report = new Report();

    public void setHeader(String header) { report.setHeader("TEXT HEADER: " + header); }
    public void setContent(String content) { report.setContent("TEXT CONTENT: " + content); }
    public void setFooter(String footer) { report.setFooter("TEXT FOOTER: " + footer); }
    public Report getReport() { return report; }
}

// HTML Report Builder
class HtmlReportBuilder implements IReportBuilder {
    private Report report = new Report();

    public void setHeader(String header) { report.setHeader("<h1>" + header + "</h1>"); }
    public void setContent(String content) { report.setContent("<p>" + content + "</p>"); }
    public void setFooter(String footer) { report.setFooter("<footer>" + footer + "</footer>"); }
    public Report getReport() { return report; }
}

// Director
class ReportDirector {
    public Report constructReport(IReportBuilder builder, String h, String c, String f) {
        builder.setHeader(h);
        builder.setContent(c);
        builder.setFooter(f);
        return builder.getReport();
    }
}

public class Main2 {
    public static void main(String[] args) {
        ReportDirector director = new ReportDirector();

        Report textReport = director.constructReport(new TextReportBuilder(),
                "Тақырып", "Мазмұн", "Аяқталды");
        textReport.show();

        Report htmlReport = director.constructReport(new HtmlReportBuilder(),
                "HTML Header", "HTML Content", "HTML Footer");
        htmlReport.show();
    }
}
