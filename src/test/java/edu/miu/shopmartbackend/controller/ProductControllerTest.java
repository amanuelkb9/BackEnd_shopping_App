package edu.miu.shopmartbackend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.shopmartbackend.model.dto.ProductDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.ProductService;
import edu.miu.shopmartbackend.service.SearchService;
import edu.miu.shopmartbackend.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @MockBean
    private SearchService searchService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link ProductController#addProduct(ProductDto, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct() {

        ProductController productController = new ProductController();
        productController
                .addProduct(new ProductDto(1L, "Product Name", 10.0d, "The characteristics of someone or something"), 1L);
    }

    /**
     * Method under test: {@link ProductController#addProduct(ProductDto, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddProduct2() {


        (new ProductController()).addProduct(mock(ProductDto.class), 1L);
    }

    /**
     * Method under test: {@link ProductController#updateProduct(ProductDto, long)}
     */
    @Test
    void testUpdateProduct() throws Exception {
        doNothing().when(productService).updateProduct((ProductDto) any(), anyLong());

        ProductDto productDto = new ProductDto();
        productDto.setDescription("The characteristics of someone or something");
        productDto.setPrice(10.0d);
        productDto.setProductId(1L);
        productDto.setProductName("Product Name");
        String content = (new ObjectMapper()).writeValueAsString(productDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/products/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/products/{id}", 1L);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/products/{id}", 1L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

