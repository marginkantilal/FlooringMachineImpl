/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.service;

import com.mycompany.flooringmaster.dao.OrderDao;
import com.mycompany.flooringmaster.dao.OrderPersistenceException;
import com.mycompany.flooringmaster.dao.OrderProdDaoStubFileImpl;
import com.mycompany.flooringmaster.dao.ProductDaoFileImpl;
import com.mycompany.flooringmaster.dao.StatesDao;
import com.mycompany.flooringmaster.dao.StatesDaoFileImpl;
import com.mycompany.flooringmaster.dto.Order;
import com.mycompany.flooringmaster.dto.States;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ritheenhep
 */
public class OrderServiceImplTest {

    LocalDate todayDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
    String formatDateTime = todayDate.format(formatter);
    String writeDate = formatter.format(todayDate);

    private OrderServiceImpl service;

    public OrderServiceImplTest() {
        OrderDao dao = new OrderProdDaoStubFileImpl();
        StatesDao state = new StatesDaoFileImpl();
        ProductDaoFileImpl prod = new ProductDaoFileImpl();
        service = new OrderServiceImpl(dao, state, prod);

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() throws OrderPersistenceException, IOException {
        States state = new States("MN");
        state.setStateTax(BigDecimal.ZERO);
        Order order = new Order();
        order.setOrderDate(todayDate);
        order.setCustomerName("jo");
        order.setOrderNumber(3);
        order.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        order.setLaborCost(BigDecimal.ZERO);
        order.setArea(BigDecimal.ONE);
        order.setProductType("Wood");
        order.setState("MN");
        order.setTaxRate(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ONE);
        order.setTotal(BigDecimal.ONE);
        service.addOrder(order);
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of addOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testAddOrder() throws Exception {
        States state = new States("MN");
        state.setStateTax(BigDecimal.ZERO);
        Order order = new Order();
        order.setCustomerName("jo");
        order.setOrderNumber(4);
        order.setLaborCostPerSquareFoot(BigDecimal.ZERO);
        order.setLaborCost(BigDecimal.ZERO);
        order.setArea(BigDecimal.ONE);
        order.setProductType("Wood");
        order.setState("MN");
        order.setTaxRate(BigDecimal.ZERO);
        order.setTotalTax(BigDecimal.ONE);
        order.setTotal(BigDecimal.ONE);
        service.addOrder(order);
        Order addOrder = service.getOrder(todayDate, 3);
        assertEquals(3, addOrder.getOrderNumber());

    }

    /**
     * Test of updateOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testUpdateOrder() throws Exception {

        Order order = service.getOrder(todayDate, 3);

        order.setProductType("Wood");
        order.setArea(BigDecimal.ONE);

        service.updateOrder(order);

        Order update = service.getOrder(todayDate, 3);
        assertEquals("Wood", order.getProductType());
        assertEquals(BigDecimal.ONE, order.getArea());
    }

    /**
     * Test of getAllOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testGetAllOrder() throws Exception {

    }

    /**
     * Test of getOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testGetOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class OrderServiceImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {

        Order order = service.getOrder(todayDate, 1);
        service.removeOrder(order);
        Order remove = service.getOrder(todayDate, 1);
        assertNull(remove);
    }

    /**
     * Test of saveWork method, of class OrderServiceImpl.
     */
    @Test
    public void testSaveWork() throws Exception {

    }

}
