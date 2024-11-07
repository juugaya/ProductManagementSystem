/*package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String searchQuery);
}
*/
// コード全部書き換え
package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String searchQuery);
}

// findByNameContaining(String searchQuery);
// JPAのメソッド名によるクエリ生成機能を利用したものです
// 渡された引数の文字列(searchQuery)を含む」全ての
// エンティティを検索するためのクエリを生成します。
// 生成されたクエリは、その名前が searchQuery を含むすべてのレコードを返します

