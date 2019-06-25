package com.yh.spring.ssm.util;



import java.util.ArrayList ;
import java.util.List ;

// 切割list
public class ListSplit {
	 List<List<Long>>	eList	= new ArrayList<List<Long>>( ) ;
	 List<Long>			gList ;
	 
	 public List<List<Long>> SplitList( List<Long> sList , int num ) {
		  int size = ( sList.size( ) ) / num ;
		  int size2 = ( sList.size( ) ) % num ;
		  
		  int j = 0 ;
		  int xx = 0 ;
		  if ( size < 2 ) {
				System.out.println( "不允许创建数量为" + "\t" + size + "\t" + "的线程" ) ;
		  }
		  for ( int i = 0 ; i < num ; i++ ) {
				gList = new ArrayList<Long>( ) ;
				
				for ( j = xx ; j < ( size + xx ) ; j++ ) {
					 gList.add( sList.get( j ) ) ;
				}
				xx = j ;
				eList.add( gList ) ;
		  }
		  if ( size2 != 0 ) {
				gList = new ArrayList<Long>( ) ;
				for ( int y = 1 ; y < size2 + 1 ; y++ ) {
					 gList.add( sList.get( sList.size( ) - y ) ) ;
				}
				eList.add( gList ) ;
		  }
		  return eList ;
	 }
}