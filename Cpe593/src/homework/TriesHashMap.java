package homework;
/*
 * TriesHashmap
 * Author: Chengzhi NI
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.Random;


public class TriesHashMap implements Serializable{

	
	TriesNode root;
	MyHashmap[][] map_matrix;
	
	private static class TriesNode{
		boolean isEnd;
		TriesNode[] child;
		public TriesNode() {
			isEnd = false;
			child = new TriesNode[26];
		}
		
	}
	public TriesHashMap() {
		root = new TriesNode();
		map_matrix = new MyHashmap[26][26];
	}
	public void insert(String word) {
		Random rand = new Random();
		if(word.length() >2) {
			int first_index = word.charAt(0) - 'a';
			int second_index = word.charAt(1) - 'a';
			if(map_matrix[first_index][second_index]==null) {
				//after hundreds of exprements, first char at 20 ,second char at 13 have the most collesion
				//so i need raise the capacity for this hashmap
				if(first_index == 20 && second_index == 13) {
					MyHashmap temp_map = new MyHashmap(500);
					temp_map.put(new Word(word.substring(2)));
					map_matrix[first_index][second_index] = temp_map;
				}
				else {
					int ran_cap = rand.nextInt(300)+ 300;
					MyHashmap temp_map = new MyHashmap(ran_cap);
					temp_map.put(new Word(word.substring(2)));
					map_matrix[first_index][second_index] = temp_map;
				}
				
				
			}
			else {
				map_matrix[first_index][second_index].put(new Word(word.substring(2)));
			}
			return;
		}
		TriesNode cursor = root;
		for(int i = 0; i < word.length();i++) {
			
			int index = word.charAt(i) - 'a';
			if(cursor.child[index] == null){
				cursor.child[index] = new TriesNode();
			}
			cursor = cursor.child[index];
			if((i == 1 || i == 0) && (i == (word.length() -1))) {
				cursor.isEnd = true;
			}
		}
		
	}
	
	
	public boolean search(String word) {
		if(word.length() <=2) {
			TriesNode cursor = root;
			for(int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if(cursor.child[index] == null || 
						((i+1) == word.length() && (cursor.child[index].isEnd == false))) 
				{
					return false;
				}
				cursor = cursor.child[index];
			}
			return true;
		}
		int first_index = word.charAt(0) - 'a';
		int second_index = word.charAt(1) - 'a';
		return map_matrix[first_index][second_index].search(word.substring(2));
		
	}
	private static class Word{
		String str;
		public Word(String str) {
			this.str = str;
		}
		@Override
		public int hashCode() {
			int res = 0;
			for(int i = 0; i < str.length(); i++) {
				int index = str.charAt(i) - 'a';
				res += (i+1) * (index +1); 
			}
			return res;
		}
		public String toString() {
			return this.str;
		}
	}
	private static class MyHashmap{
		private static int default_initial_capacity = 8;
		int col_count;
		private int capacity;
		private float load_factor = 0.75f;
		private int size;
		private Entry[] buckets;
		private static Random rand = new Random();
		int p;
		int a;
		int b;
		
		private ArrayList<String> collision_words;
		private static ArrayList<Integer> primes = segment_prime_sieve(1385500l, 1385500*2);
		private ArrayList<Word> word_set;
		
		private static class Entry<K,V>{
			K key;
			V value;
			public Entry(K key, V value) {
				this.key = key;
				this.value = value;
				
			}
			
		}
		
		public MyHashmap() {
			this(default_initial_capacity);
			
		}
		public MyHashmap(int m) {
			this.capacity = topowertwo(m);
			this.size = 0;
			this.buckets = new Entry[capacity];
			//rand.setSeed(10422527);
			
			p = primes.get(rand.nextInt(primes.size()));
			a = rand.nextInt(p) + 1;
			b =a;
			while(b == a) {
				b = rand.nextInt(p-1);
			}
			
			col_count = 0;
			collision_words = new ArrayList<>();
			word_set = new ArrayList<>();
		}
		public int topowertwo(int m) {//make m always be power of two
									  // then n%m could be n & (m-1)
			capacity = 1;
			while(capacity < m) {
				capacity <<= 1;
			}
			return capacity;
		}
		//universal hash
		public int universalhash(int code, int m) {
			int res_code = (this.a * code + this.b) % this.p;
			res_code = res_code & (m -1);
			return res_code;
			
		}
		public void grow() {
			
			this.capacity <<=1;
			this.buckets = new Entry[capacity];
			this.size = 0;
			
			ArrayList<Word> old = new ArrayList<>(word_set);
			word_set = new ArrayList<>();
			for(Word word: old) {
				this.put(word);
			}
			/*//System.out.println(buckets.length);
			Entry[] temp = new Entry[this.capacity];
			//System.out.println(temp.length);
			for(int i = 0; i < used_index.size(); i++) {
				int origin_index = used_index.get(i);
				int wordhashvalue = (int) buckets[origin_index].key;
				int new_hashindex = this.universalhash(wordhashvalue,this.capacity);
				if(temp[new_hashindex] == null) {
					//System.out.println("emptyed");
					temp[new_hashindex] = buckets[origin_index];
				}else {
					System.out.println("not empty");
					while(temp[new_hashindex] != null) {
						new_hashindex += 1;
					}
					temp[new_hashindex] = buckets[origin_index];
				}
				
				used_index.set(i, new_hashindex);
			}
			//buckets = null;
			this.buckets = temp;
			//System.out.println(buckets.length);*/
			
		}
		//add element with perfect hash
		public void put(Word word) {
			word_set.add(word);
			if(this.capacity * load_factor <= size) 
				this.grow();
				//this.capacity *=2;
				
				
			
			int wordhashvalue = word.str.hashCode();
			
			int hashindex = this.universalhash(wordhashvalue,this.capacity);
			
			if(this.buckets[hashindex] == null) {
				int rand_cap = rand.nextInt(300)+ 1000;
				MyHashmap new_map = new MyHashmap(rand_cap);
				int word_index = new_map.universalhash(wordhashvalue, new_map.capacity);
				//System.out.println(word_index);
				Entry<Integer, Word> word_entry = new Entry<>(wordhashvalue, word);
				new_map.buckets[word_index] = word_entry;
				
				Entry<Integer, MyHashmap> new_entry = new Entry<>(wordhashvalue, new_map);
				
				buckets[hashindex] = new_entry;
				
				size++;
			}
			else {
				
				MyHashmap woo = (MyHashmap) buckets[hashindex].value;
				int internal_hash_index = woo.universalhash(wordhashvalue, woo.capacity);
				if(woo.buckets[internal_hash_index] == null) {
					Entry<Integer, Word> word_entry = new Entry<>(wordhashvalue, word);
					woo.put_word(word_entry);
					//size++;
				}
				else {
					//System.out.println(hashindex);
					//System.out.println(word);
					collision_words.add(word.str);
					col_count ++;
				}
			}
		
			
		}
		
		public void put_word(Entry<Integer, Word> word_entry) {
			//if(capacity * load_factor <= size)
				//grow();
			int wordhashvalue = word_entry.key;
			int hashindex = universalhash(wordhashvalue, capacity);
			buckets[hashindex] = word_entry;
		}
		public boolean search_map(String str) {
			str=str.trim().toLowerCase();
			Word word = new Word(str);
			int str_hashcode = word.str.hashCode();
			int hashindex = universalhash(str_hashcode,this.capacity);
			if(buckets[hashindex] == null)
				return false;
			
			MyHashmap map = (MyHashmap) this.buckets[hashindex].value;
			int internalcode = map.universalhash(str_hashcode, map.capacity);
			if(map.buckets[internalcode] == null)
				return false;
			return true;
			
		}
		
		public boolean search_colwords(String str) {
			if(this.collision_words.size() == 0)
				return false;
			for(String col_word: this.collision_words) {
				if(col_word.equals(str))
					return true;
			}
			return false;
		}
		
		public boolean search(String str) {
			if(search_map(str) || search_colwords(str))
				return true;
			return false;
		}
		public static long[] euler_prime_sieve(long max) {
			long[] primes = new long[(int)(max/3)+1];
			boolean[] isprime = new boolean[(int)max+1];
			int count =0;
			for(int m = 2; m<=max; m++) {
				if(!(isprime[m])) {
					primes[count++] =m;
				}
				for(int j=0;j<count && m*primes[j] <= max;j++) {
					isprime[(int)(primes[j]*m)] = true;
					if(m%primes[j]==0) {
						break;
					}
				}
				
			}
			return primes;
		}
		public static ArrayList<Integer> segment_prime_sieve(long low, long high) {
			long[] sqrt_primes = euler_prime_sieve((int)Math.sqrt(high));
			boolean[] isprime = new boolean[(int) (high-low+1)];
			
			for(long prime:sqrt_primes) {
				if(prime == 0) {
					break;
				}
				long init = (low/prime)*prime;
				
				for(long i =init;i<=high;i=i+prime) {
					
					if(i>=low) {
						isprime[(int)(i-low)] = true;
						
					}
					
				}
				
			}
			ArrayList<Integer> re = new ArrayList<>();
			for(int i = 0; i < isprime.length;i++) {
				if(!isprime[i]) {
					re.add((int)(i+low));
				}
			}
			return re;
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<>();
		TriesHashMap test = new TriesHashMap();
		String line=null;
		String filename = "dict.txt";
		try
        {
                BufferedReader in=new BufferedReader(new FileReader(filename));
                
                while ((line = in.readLine())!=null)
                {
                	line=line.trim().toLowerCase();
                	try {
                		words.add(line);
                		test.insert(line);
                	}
                	catch (java.lang.NullPointerException e) {
                		
                	}
                }
                in.close();
        } catch (IOException e)
        {
                e.printStackTrace();
        }
        System.out.println("histogram of collisions");
        int total_col = 0;
        for(int i = 0; i < 26; i ++) {
        	for(int j = 0; j < 26; j ++) {
        		if(test.map_matrix[i][j] != null) {
        			if(test.map_matrix[i][j].col_count > 0){
        				int colc = test.map_matrix[i][j].col_count;
        				total_col += colc;
        				StringBuilder sb = new StringBuilder();
        				sb.append(Character.toString((char)(i + 'a')));
        				sb.append(",");
        				sb.append(Character.toString((char)(j + 'a')));
        				sb.append(":");
        				for(int h = 0; h<colc; h++) {
        					sb.append("*");
        				}
        				System.out.println(sb.toString());
        			}
        		}
        	}
        }
        
        System.out.println("total collision is: "+total_col);
        String checkfile ="spell.txt";
        System.out.println("not correct word is: ");
        try
        {
                BufferedReader in=new BufferedReader(new FileReader(checkfile));
                
                while ((line = in.readLine())!=null)
                {
                	line=line.trim().toLowerCase();
                	try {
                		if(!test.search(line)) {
                			System.out.println(line);
                		}
                	}
                	catch (java.lang.NullPointerException e) {
                		
                	}
                }
                in.close();
        } catch (IOException e)
        {
                e.printStackTrace();
        }
        
		
		
		
	}
}
