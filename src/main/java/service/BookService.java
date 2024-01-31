package service;

import bean.Book;
import bean.ReaderBook;
import dao.BookDao;
import dao.ReaderBookDao;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author FARO_Z
 * @Date 2021/1/6 ����12:43
 * @Version 1.0
 **/
public class BookService {
    /**
     * ��ȡ���߽��ĵ�����ͼ��
     * @param readerID
     * @return
     */
    public List<Book> getBorrowBook(int readerID) {
        List<Book> books = new ArrayList<>();
        ReaderBookDao readerBookDao = new ReaderBookDao();
        List<ReaderBook> list = readerBookDao.list();
        //ɸѡָ��id�Ķ���
        list.stream()
                .filter(user -> user.getReader_id()==readerID)
                .collect(Collectors.toList());
        //��ȡ����ͼ����Ϣ
        List<Book> bookList = new BookDao().list();
        Map<Integer, Book> bookMap = new HashMap<>();
        //��ͼ��ISBN��ź�ͼ��������map
        for (Book book : bookList) {
            bookMap.put(book.getISBN(),book);
        }
        //�����߽��ĵ�ͼ�飬����������
        for (ReaderBook readerBook : list) {
            books.add(bookMap.get(readerBook.getBook_ISBN()));
        }
        return books;
    }
}
