package com.joymove.util;
//writer: renjunqu

public class Functools {

	
	
		public static Integer checkerForEach(Object []checkers,Object[] checker_args) {
			int checkerIndex = 0;
			Integer re = 0;
			   for(;checkerIndex < checkers.length;checkerIndex++) {
				   Function<Object,Integer> check = (Function<Object,Integer>) checkers[checkerIndex];
				   re = check.apply(checker_args[checkerIndex]);
				   if(re !=  0)
					   break;
			   }
		
			 return re;
		}
}


