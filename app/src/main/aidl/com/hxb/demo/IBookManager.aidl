// IBookManager.aidl
package com.hxb.demo;
import com.hxb.demo.Book;

interface IBookManager {

   List<Book> getBookList();
   void addBook(in Book book);

}