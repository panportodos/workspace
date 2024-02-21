package com.study.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.product.config.DBConnectionMgr;
import com.study.product.vo.ProductVo;

public class ProductDao {

	private static ProductDao instance;
	private DBConnectionMgr pool;

	private ProductDao() {
		pool = DBConnectionMgr.getInstance();
	}

	public static ProductDao getInstance() {
		if (instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}

	// Select
	public List<ProductVo> getProductList() {
		List<ProductVo> list = new ArrayList<>(); //
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = pool.getConnection();
			String sql = "select * from product_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVo productVo = ProductVo.builder().productId(rs.getInt(1)).productName(rs.getString(2))
						.productPrice(rs.getInt(3)).productSize(rs.getString(4)).build();
				list.add(productVo);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return list;
	}

	public ProductVo findProductByProductName(String productName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVo productVo = null;

		try {
			con = pool.getConnection();
			String sql = "select * from product_tb where product_name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				productVo = ProductVo.builder().productId(rs.getInt(1)).productName(rs.getString(2))
						.productPrice(rs.getInt(3)).productSize(rs.getString(4)).build();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return productVo;

	}

	public int save(ProductVo productVo) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = pool.getConnection();
			String sql = "insert into product_tb values(0,?,?,?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // auto increment된 키값을 받아오는 작업
			pstmt.setString(1, productVo.getProductName());
			pstmt.setInt(2, productVo.getProductPrice());
			pstmt.setString(3, productVo.getProductSize());
			successCount = pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				productVo.setProductId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return successCount;

	}
}

//	try
//
//	{
//		con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
//		String sql = "select * from product_tb where product_name = ?";
//		pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, productName);
//		rs = pstmt.executeQuery();
//
//		if (rs.next()) {
//			product = ProductVo.builder().productName(rs.getString(2)).Price(rs.getInt(3)).Size(rs.getString(4))
//					.build();
//		}
//	}catch(
//	Exception e)
//	{
//		e.printStackTrace();
//	}finally
//	{
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//			if (pstmt != null) {
//				pstmt.close();
//			}
//			if (con != null) {
//				con.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	return product;
//
//	}
//
//	public int saveProduct(ProductVo productVo) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		int successCount = 0;
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//
//			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
//			String sql = "insert into product_tb(product_name, product_price, product_size) values(?,?,?)";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, product.getName());
//			pstmt.setInt(2, product.getPrice());
//			pstmt.setString(3, product.getSize());
//			successCount = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (con != null) {
//					con.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return successCount;
//	}
//}
