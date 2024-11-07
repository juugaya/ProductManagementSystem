// 商品情報を操作するためのコントローラを作ります。このクラスはWeb上の操作を受け付けて、それに応じて処理を行う役割があります。
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// Springフレームワークから必要なクラスをインポートします。これらのクラスがWeb操作とそれに応じた処理をつなげる役割を果たします。
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.entity.Product;
// 商品情報の操作を行うサービスをインポートします。このサービスクラスが実際の操作（保存、取得、削除）を行います。
import com.example.service.ProductService;

// '@Controller'と書いてあるのは、このクラスがWebからの操作を受け付ける役割を果たすという意味です。
@Controller
public class ProductController {
	@Autowired
    private ProductService productService;
    // 商品操作サービスのインスタンス（実体）を作ります。このサービスが実際の商品操作を行います。
    //private final ProductService productService;

    // '@Autowired'と書いてあるのは、この下に書かれている部分（商品操作サービス）が自動的に準備されるという意味です。
    // このクラス内のどこからでも、この商品操作サービスを利用できるようになります。
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Web上で'/'（ホームページ）にアクセスしたときの処理を書きます。
    @GetMapping("/")
    public String viewHomePage(Model model) {
        // 商品操作サービスから全ての商品情報を取得します。
        List<Product> listProducts = productService.getAllProducts();
        // 取得した商品情報を画面（ビュー）に渡します。画面上でこの情報を利用できるようになります。
        model.addAttribute("listProducts", listProducts);
        // 表示する画面（ビュー）の名前を返します。この場合、'index'という名前の画面を表示します。
        return "index";
    }

    // 商品を新しく作るための画面にアクセスしたときの処理を書きます。
    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model) {
        // 新しい商品情報を作ります。
        Product product = new Product();
        // 新しい商品情報を画面（ビュー）に渡します。
        model.addAttribute("product", product);
        // 商品を新しく作る画面（'create'）を表示します。
        return "create";
    }

    // Web上で新しい商品情報を送信したとき（保存ボタンを押したとき）の処理を書きます。
    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {
        // 商品操作サービスを使って、新しい商品情報を保存します。
        productService.saveProduct(product);
        // 保存が完了したら、ホームページに戻ります。
        return "redirect:/";
    }

    // 商品情報を更新するための画面にアクセスしたときの処理を書きます。
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // 更新する商品の情報を取得します。
        Product product = productService.getProductById(id).orElse(null);
        // 取得した商品情報を画面（ビュー）に渡します。
        model.addAttribute("product", product);
        // 商品情報を更新する画面（'edit'）を表示します。
        return "edit";
    }

    // 商品情報を削除するときの処理を書きます。
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id) {
        // 商品操作サービスを使って、指定された商品情報を削除します。
        this.productService.deleteProduct(id);
        // 削除が完了したら、ホームページに戻ります。
        return "redirect:/";
    }

    // 商品の詳細情報を表示する画面にアクセスしたときの処理を書きます。
    @GetMapping("/productDetail/{id}")
    public String productDetail(@PathVariable(value = "id") long id, Model model) {
        // 詳細を表示する商品の情報を取得します。
        Product product = productService.getProductById(id).orElse(null);
        // 取得した商品情報を画面（ビュー）に渡します。
        model.addAttribute("product", product);
        // 商品の詳細情報を表示する画面（'detail'）を表示します。
        return "detail";
    }
    
//    @GetMapping("/search")
//    public String search(@RequestParam("searchQuery") String searchQuery, Model model) {
//        List<Product> listProducts = productService.searchProducts(searchQuery);
//        model.addAttribute("listProducts", listProducts);
//        return "index";
//    }

//        @GetMapping("/search")
//        public List<Product> searchProducts(@RequestParam String keyword) {
//            return productService.searchProducts(keyword);
//        }

    @GetMapping("/search") public String searchProducts(@RequestParam String keyword, Model model) 
    { List<Product> products = productService.searchProducts(keyword); 
    model.addAttribute("products", products); return "search";
    }
    
    
    
    
}
