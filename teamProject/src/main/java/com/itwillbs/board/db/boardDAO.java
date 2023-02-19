package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class boardDAO {
	
	public Connection getConnection() throws Exception{
		Context init=new InitialContext();
		DataSource ds=(DataSource)init.lookup("java:comp/env/jdbc/MysqlDB");
		Connection con=ds.getConnection();
		return con;
	}
	
	public void insertBoard(boardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			
			int market_id = 1;
			String sql = "select max(market_id) from market";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				market_id = rs.getInt(1)+1;
			}
			System.out.println("체크1");
			sql="insert into market(market_id,insert_id,title,content,insert_date,book_price,book_st,book_type,trade_inperson,trade_st,trade_type) "
				+"values(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, market_id);  
			pstmt.setString(2, dto.getInsert_id()); 
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setTimestamp(5, dto.getInsert_date());
			pstmt.setString(6,dto.getBook_price());
			pstmt.setString(7,dto.getBook_st());
			pstmt.setString(8,dto.getBook_type());
			pstmt.setString(9,dto.getTrade_inperson());
			pstmt.setString(10,dto.getTrade_st());
			pstmt.setString(11,dto.getTrade_type());
			
			pstmt.executeUpdate();
			System.out.println("체크2");
	
			sql="insert into market_image(market_id, url) values(?,?)";	
				
			for(int i = 0; i < dto.getImgUrls().length; i++ ) {
				pstmt=con.prepareStatement(sql);
					
				pstmt.setInt(1, market_id); 
				pstmt.setString(2, dto.getImgUrls()[i]); 
					
				pstmt.executeUpdate();
			} 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}
	
	public ArrayList<boardDTO> getBoardList(int start, int num) {
		ArrayList<boardDTO> dtolist = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = getConnection();
			
			String sql="select * from market order by market_id desc limit ?, ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, num);
			 
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				boardDTO dto = new boardDTO();
				dto.setMarket_id(rs.getInt("market_id"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setTitle(rs.getString("title"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setView_cnt(rs.getInt("view_cnt"));
				dtolist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
		return dtolist;
	}
	
	public boardDTO getBoard(int market_id) {
		boardDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = getConnection();
			
			String sql="select * from market where market_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, market_id);  
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new boardDTO();
				dto.setMarket_id(rs.getInt("market_id"));
				dto.setInsert_id(rs.getString("insert_id"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setView_cnt(rs.getInt("view_cnt"));
				dto.setInsert_date(rs.getTimestamp("insert_date"));
				dto.setBook_price(rs.getString("book_price"));
				dto.setBook_st(rs.getString("book_st"));
				dto.setBook_type(rs.getString("book_type"));
				dto.setTrade_st(rs.getString("trade_st"));
				dto.setTrade_type(rs.getString("trade_type"));
				dto.setTrade_inperson(rs.getString("trade_inperson"));
			}
			
			sql="select * from market_image where market_id=?"; 
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, market_id);  
			 
			rs=pstmt.executeQuery();
			
			String[] url = new String[boardDTO.getImgLengthMax()];
			int i = 0;
			while(rs.next()) {
				url[i] = rs.getString("url");
				if (i <=boardDTO.getImgLengthMax()-2) {i++;}
			}
			dto.setImgUrls(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
		return dto;
	}
	
	public void updateReadCount(boardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {	
			con = getConnection();
			
			String sql="update market set view_cnt=view_cnt+1 where market_id=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getMarket_id());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}
	
	public void updateBoard(boardDTO dto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = getConnection();
			
			String sql="update market set title=?, content=?, book_price=?, book_st=?, book_type=?, trade_st=?, trade_type=?, trade_inperson=? where market_id=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());  
			pstmt.setString(2, dto.getContent());  
			pstmt.setString(3, dto.getBook_price());
			pstmt.setString(4, dto.getBook_st());
			pstmt.setString(5, dto.getBook_type());
			pstmt.setString(6, dto.getTrade_st());
			pstmt.setString(7, dto.getTrade_type());
			pstmt.setString(8, dto.getTrade_inperson());
			pstmt.setInt(9, dto.getMarket_id());
			
			pstmt.executeUpdate();
			
			// minImageId setting start
			int minImageId = 0;
			sql="select min(image_id) from market_image where market_id=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getMarket_id()); 
 
			rs=pstmt.executeQuery();
			
			System.out.println("minImageId : "+minImageId);
			
			if(rs.next()) {
				minImageId = rs.getInt("min(image_id)");
			}
			// minImageId setting end
			
			sql="update market_image set url=? where market_id=? and image_id=?";	
			
			for(int i = 0; i < dto.getImgUrls().length; i++ ) {
				pstmt=con.prepareStatement(sql);
					
				pstmt.setString(1, dto.getImgUrls()[i]); 
				pstmt.setInt(2, dto.getMarket_id());
				pstmt.setInt(3, minImageId+i);
					
				pstmt.executeUpdate();
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
	}
	
	public void deleteBoard(int market_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {	
			con = getConnection();
			
			String sql="delete from market_image where market_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, market_id);
			pstmt.executeUpdate();
			
			sql="delete from market where market_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, market_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
		}
	}
	
	public int getBoardPage() {
		int allPage = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {	
			con = getConnection();
			
			String sql="select count(*) from market";
			pstmt=con.prepareStatement(sql);
 
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				allPage = rs.getInt("count(*)");
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try { pstmt.close();} catch (Exception e2) {}
			if(con!=null) try { con.close();} catch (Exception e2) {}
			if(rs!=null) try { rs.close();} catch (Exception e2) {}
		}
		return allPage;
	}
		
	
	
}// class
