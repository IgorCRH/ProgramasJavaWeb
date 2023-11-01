import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PrintHeadersServlet")
public class PrintHeadersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PrintHeadersServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Headers da Requisição HTTP e Data/Hora Atual</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Headers da Requisição HTTP e Data/Hora Atual</h1>");

        // Obtém a data e hora atuais
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        out.println("<p>Data e Hora Atuais: " + formattedDate + "</p>");

        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Nome do Header</th>");
        out.println("<th>Valor</th>");
        out.println("</tr>");

        // Loop para listar os headers da requisição HTTP
        java.util.Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            out.println("<tr>");
            out.println("<td>" + headerName + "</td>");
            out.println("<td>" + headerValue + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");

        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
