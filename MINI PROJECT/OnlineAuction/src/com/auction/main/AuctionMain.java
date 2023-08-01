package com.auction.main;

import java.util.Scanner;

import com.auction.dao.BettingDao;
import com.auction.dao.BettingDaoImpl;
import com.auction.dao.CustomerDao;
import com.auction.dao.CustomerDaoImpl;
import com.auction.dao.SellerDao;
import com.auction.dao.SellerDaoImpl;
import com.auction.model.Betting;
import com.auction.model.Customer;
import com.auction.model.Seller;

public class AuctionMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerDao customerDao = new CustomerDaoImpl();
        BettingDao bettingDao = new BettingDaoImpl();
        SellerDao sellerDao = new SellerDaoImpl();

        System.out.println("Welcome to Online Auction System");

        while (true) {
            System.out.println("\n1. Register as Customer\n2. Place Bid\n3. Sell Product\n4. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Customer customer = new Customer();
                    System.out.println("Enter your name: ");
                    customer.setCustomerName(scanner.next());

                    System.out.println("Enter your email: ");
                    customer.setCustomerEmail(scanner.next());

                    System.out.println("Enter your password: ");
                    customer.setPassword(scanner.next());
                    scanner.nextLine();
                    System.out.println("Enter your phone number: ");
                    customer.setPhone(scanner.nextLong());

                    customerDao.create(customer);
                    break;

                case 2:
                    System.out.println("Enter your email: ");
                    String bidderEmail = scanner.next();

                    Customer bidder = customerDao.getCustomerByEmail(bidderEmail);
                    if (bidder == null) {
                        System.out.println("Bidder with the given email does not exist. Please register first.");
                        break;
                    }

                    System.out.println("Enter the product you want to bid for: ");
                    String product = scanner.next();

                    System.out.println("Enter your bid amount: ");
                    long bidAmount = scanner.nextLong();

                    Betting highestBid = bettingDao.getHighestBidForProduct(product);
                    if (highestBid != null && bidAmount <= highestBid.getBettingAmount()) {
                        System.out.println("Your bid amount should be higher than the current highest bid.");
                        break;
                    }

                    Betting newBid = new Betting();
                    newBid.setBettingProduct(product);
                    newBid.setBettingAmount(bidAmount);
                    newBid.setBidderName(bidder.getCustomerName());
                    newBid.setCustomerId(bidder.getCustomerId());

                    if (highestBid != null) {
                        bettingDao.updateHighestBid(newBid);
                    } else {
                        bettingDao.create(newBid);
                    }

                    System.out.println("Your bid has been placed successfully.");
                    break;

                case 3:
                    System.out.println("Enter your email: ");
                    String sellerEmail = scanner.next();

                    Customer sellerCustomer = customerDao.getCustomerByEmail(sellerEmail);
                    if (sellerCustomer == null) {
                        System.out.println("Seller with the given email does not exist. Please register first.");
                        break;
                    }

                    System.out.println("Enter the product you want to sell: ");
                    String sellerProduct = scanner.next();

                    Seller seller = new Seller();
                    seller.setSellerProduct(sellerProduct);
                    seller.setSellerName(sellerCustomer.getCustomerName());
                    seller.setCustomerId(sellerCustomer.getCustomerId());

                    sellerDao.create(seller);

                    System.out.println("Your product has been listed for sale.");
                    break;

                case 4:
                    System.out.println("Thank you for using the Online Auction System.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
