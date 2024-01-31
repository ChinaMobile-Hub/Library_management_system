package servlet;

import bean.Book;
import dao.BookDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddBookServlet")
public class AddBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //这里写业务代码
        int ISBN = Integer.parseInt(request.getParameter("ISBN"));
        float price = Float.parseFloat(request.getParameter("price"));
        int num = Integer.parseInt(request.getParameter("num"));
        String name = request.getParameter("name");
        String writer = request.getParameter("writer");

        BookDao bookDao = new BookDao();
        Book book = new Book(name, price, num, writer, ISBN);
        bookDao.add(book);

        //点击确认，添加业务代码后，要将页面跳转回管理界面
        // request.getRequestDispatcher("/managebook.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
