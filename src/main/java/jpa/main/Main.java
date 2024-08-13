package jpa.main;

import jpa.config.Config;
import jpa.entity.*;
import jpa.repository.BookRepository;
import jpa.repository.CategoryRepository;
import jpa.repository.OrderDetailsRepository;
import jpa.repository.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static ApplicationContext context= new AnnotationConfigApplicationContext(Config.class);
    static BookRepository bookRepository=(BookRepository) context.getBean("bookRepository");
    static CategoryRepository categoryRepository =(CategoryRepository) context.getBean("categoryRepository");
    static OrderDetailsRepository orderDetailsRepository=(OrderDetailsRepository) context.getBean("orderDetailsRepository");
    static OrderRepository orderRepository =(OrderRepository) context.getBean("orderRepository");


    public static void main(String[] args){

//        createNewBookEntryWithNewCategory();
//        createNewBookEntry();
//        findByAuthor("Roger");
//        findByNameAndAuthor("Java A-Z","Roger");
//        findByNameOrAuthor("linux","Roger");
//        findByPriceLessThan(80);
//        findByBookDetailsIsbn("ISIBF1219323");
//        findByNameContaining("ava");

        System.out.println("=========");
//        createNewOrder();
        createNewOrderDetailsEntry();

    }

    // ===========BOOKS============
//    public static void findByAuthor(String author){
//        List<BookEntity> bookEntityList=bookRepository.findByAuthor(author);
//        if(bookEntityList!=null){
//            System.out.println("Find "+bookEntityList.size()+" books which author = "+author);
//            System.out.println("They are: ");
//            for(BookEntity bookEntity:bookEntityList){
//                System.out.println(bookEntity.toString());
//            }
//        }
//    }
//
//    public static void findByNameAndAuthor(String name,String author){
//        List<BookEntity> bookEntityList=bookRepository.findByNameAndAuthor(name,author);
//        if(bookEntityList!=null){
//            System.out.println("\nFind "+bookEntityList.size()+" books which name= "+ name+ " and author = "+author);
//            for(BookEntity bookEntity:bookEntityList){
//                System.out.println(bookEntity.toString());
//            }
//        }
//    }
//
//    public static void findByNameOrAuthor(String name,String author){
//        List<BookEntity> bookEntityList=bookRepository.findByNameOrAuthor(name,author);
//        if(bookEntityList!=null){
//            System.out.println("\nFind "+bookEntityList.size()+" books which name= "+ name+ "or author = "+author);
//            for(BookEntity bookEntity:bookEntityList){
//                System.out.println(bookEntity.toString());
//            }
//        }
//    }

//    public static void findByPriceLessThan(int price){
//        List<BookEntity> bookEntityList=bookRepository.findByPriceLessThan(price);
//        if(bookEntityList!=null){
//            System.out.println("\nFind "+bookEntityList.size()+" books price less than "+price);
//            for(BookEntity bookEntity:bookEntityList){
//                System.out.println(bookEntity.toString());
//            }
//        }
//    }
//
//    public static void findByNameContaining(String name){
//        List<BookEntity> bookEntityList=bookRepository.findByNameContaining(name);
//        if(bookEntityList!=null){
//            System.out.println("\nFind "+bookEntityList.size()+" books containing name "+name);
//            for(BookEntity bookEntity:bookEntityList){
//                System.out.println(bookEntity.toString());
//            }
//        }
//    }
//
//    public static void findAllUsingQuery(){
//        List<BookEntity> bookEntityList=bookRepository.getAll();
//        if(bookEntityList!=null){
//            System.out.println("\nFind "+bookEntityList.size()+" books");
//            for(BookEntity bookEntity:bookEntityList){
//                System.out.println(bookEntity.toString());
//            }
//        }
//    }
//
//    public static void findByBookDetailsIsbn(String isbn){
//        BookEntity bookEntity= bookRepository.findByBookDetailsIsbn(isbn);
//        if(bookEntity!=null){
//            System.out.println("\nFind book which isbn= "+isbn);
//
//                System.out.println(bookEntity.toString());
//
//        }
//    }
//
//
//
//    public static void createNewBookEntry(){
//        CategoryEntity categoryEntity= new CategoryEntity();
//        categoryEntity.setId(1);
//
//        BookEntity bookEntity=createNewBook();
//        bookEntity.setCategory(categoryEntity);
//        bookRepository.save(bookEntity);
//    }
//    public static void createNewBookEntryWithNewCategory(){
//        CategoryEntity categoryEntity= createNewCategory();
//        categoryRepository.save(categoryEntity);
//
//        BookEntity bookEntity=createNewBook();
//        bookEntity.setCategory(categoryEntity);
//        bookRepository.save(bookEntity);
//    }
//
//    private static CategoryEntity createNewCategory() {
//        CategoryEntity categoryEntity=new CategoryEntity();
//        categoryEntity.setName("Marketing");
//        categoryEntity.setDescription("Marketing books");
//        return categoryEntity;
//    }
//
//    private static BookEntity createNewBook() {
//        BookDetailsEntity bookDetailsEntity = new BookDetailsEntity();
//        bookDetailsEntity.setIsbn("ISIBF1219323");
//        bookDetailsEntity.setNumberOfPage(23);
//        bookDetailsEntity.setPrice(65);
//        bookDetailsEntity.setPublishDate(LocalDate.now());
//
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setName("Java A-Z");
//        bookEntity.setAuthor("Roger");
//        bookEntity.setBookDetails(bookDetailsEntity);
//        bookDetailsEntity.setBook(bookEntity);
//
//        return bookEntity;
//
//    }




// ===========ORDERS============

    public static void createNewOrderDetailsEntry(){
        OrderEntity orderEntity= createNewOrder();
        orderEntity.setId(1);
        OrderDetailsEntity orderDetailsEntity=createNewOrderDetails();
        orderDetailsEntity.setOrder(orderEntity);
        orderDetailsRepository.save(orderDetailsEntity);
    }

//
//    public static void createNewOrderDetailsEntryWithNewOrder(){
//        OrderEntity orderEntity= createNewOrder();
//        orderRepository.save(orderEntity);
//
//        OrderDetailsEntity orderDetailsEntity=createNewOrderDetails();
//        orderDetailsEntity.setOrder(orderEntity);
//        orderRepository.save(orderDetailsEntity);
//    }

        private static OrderEntity createNewOrder() {
            OrderEntity orderEntity=new OrderEntity();

            orderEntity.setOrderDate(LocalDate.now());
            orderEntity.setCustomerName("Roger");
            orderEntity.setCustomerAddress("USA");
        return orderEntity;
    }

    private static OrderDetailsEntity createNewOrderDetails() {
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setProductName("Java books");
        orderDetailsEntity.setQuantity(23);
        orderDetailsEntity.setUnitPrice(100);


        OrderEntity orderEntity = new OrderEntity();
        orderDetailsEntity.setOrder(orderEntity);

        return orderDetailsEntity;

    }

}
