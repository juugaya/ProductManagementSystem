package com.example.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Product;
import com.example.repository.ProductRepository;

public class ProductServiceTest {

    private ProductService productService;

    // Mock annotationを使ってProductRepositoryを模擬化します。
    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        // Mockitoを初期化します。
        MockitoAnnotations.initMocks(this);

        // テスト対象のProductServiceを初期化します。
        // ここでは、模擬化したProductRepositoryを使っています。
        productService = new ProductService(productRepository);
    }

    @Test
    public void testSaveProduct() {
        // 任意のProductオブジェクトを作成します。
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        // リポジトリが特定のProductを返すように振る舞いを定義します。
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // テスト対象のメソッドを呼び出します。
        Product savedProduct = productService.saveProduct(product);

        // 期待する結果と実際の結果を比較します。
        assertEquals(savedProduct, product);
    }

    // getAllProducts, getProductById, deleteProductについても同様にテストメソッドを書くことができます。
}
