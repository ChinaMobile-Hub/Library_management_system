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
        //ɾ��������Ϣ
        readerDao.delete(readerID);

        //��Ҫ����Щͼ���������1
        List<Book> changeBookList = new ArrayList<>();//���޸ĵ�ͼ���б�
        List<ReaderBook> readerBookList = readerBookDao.list();
        for (ReaderBook readerBook : readerBookList) {
            if (readerBook.getReader_id()==readerID) {
                Book book = bookDao.get(readerBook.getBook_ISBN());//��ȡ��Ӧ��book
                book.setNum(book.getNum()+1);//��ԭ������1
                changeBookList.add(book);
            }
        }
        //�޸�����book��Ϣ
        for (Book book : changeBookList) {
            bookDao.update(book);
        }

        //ɾ���ö��߽��Ĺ���ͼ�����Ϣ< ��һ��Ҫ����һ��֮�󣬲�Ȼ��һ����ȡ��������
        ReaderBookService readerBookService = new ReaderBookService();
        readerBookService.deleteByReader(readerID);

        request.getRequestDispatcher("/managereader.jsp").forward(request, response);
    }
}
