package servlet;

import bean.Book;
import bean.ReaderBook;
import dao.BookDao;
import dao.ReaderBookDao;
import dao.ReaderDao;
import service.BookService;
import service.ReaderBookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeleteReaderServlet")
public class DeleteReaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int readerID = Integer.parseInt(request.getParameter("readerID"));
        ReaderDao readerDao = new ReaderDao();
        ReaderBookDao readerBookDao = new ReaderBookDao();
        BookDao bookDao = new BookDao();
        //删除读者信息
        readerDao.delete(readerID);

        //还要将这些图书的数量加1
        List<Book> changeBookList = new ArrayList<>();//待修改的图书列表
        List<ReaderBook> readerBookList = readerBookDao.list();
        for (ReaderBook readerBook : readerBookList) {
            if (readerBook.getReader_id()==readerID) {
                Book book = bookDao.get(readerBook.getBook_ISBN());//获取对应的book
                book.setNum(book.getNum()+1);//将原数量加1
                changeBookList.add(book);
            }
        }
        //修改所有book信息
        for (Book book : changeBookList) {
            bookDao.update(book);
        }

        //删除该读者借阅过的图书的信息< 这一步要在上一步之后，不然上一步获取不到数据
        ReaderBookService readerBookService = new ReaderBookService();
        readerBookService.deleteByReader(readerID);

        request.getRequestDispatcher("/managereader.jsp").forward(request, response);
    }
}
