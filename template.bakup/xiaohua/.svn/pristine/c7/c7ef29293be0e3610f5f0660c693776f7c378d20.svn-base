package cltest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.Jedis;

/**
 * 封装redis工具
 */
public class RedisService {
	/**
	 * 从指定的列表右边出队,添加到目的列表中
	 * 
	 * @param srckey
	 *            源列表
	 * @param dstkey
	 *            　目的列表
	 * @return
	 */
	public String rpoppush(String srckey, String dstkey) {
		return this.getJedis().rpoplpush(srckey, dstkey);
	}

	/**
	 * 获取指定列表的范围数据
	 * 
	 * @param key
	 *            　列表名
	 * @param start
	 *            　开始位置
	 * @param end
	 *            　结束位置
	 * @return 
	 * @return
	 */
	public List<String> Listlrange(String key, int start, int end) {
		return this.getJedis().lrange(key, start, end);
	}

	/**
	 * 从队列的左边取出一条数据
	 * 
	 * @param key
	 *            　列表名
	 * @return
	 */
	public String lpop(String key) {
		return this.getJedis().lpop(key);
	}

	/**
	 * 从队列的右边取出一条数据
	 * 
	 * @param key
	 *            列表名
	 * @return
	 */
	public String rpop(String key) {
		return this.getJedis().rpop(key);
	}

	/**
	 * 把一个值添加到对应列表中
	 * 
	 * @param key
	 *            列表名
	 * @param index
	 *            　添加的位置
	 * @param value
	 *            　数据
	 * @return
	 */
	public String lset(String key, long index, String value) {
		return this.getJedis().lset(key, index, value);
	}

	/**
	 * 把所有数据添加到一个列表中
	 * 
	 * @param key
	 *            列表名
	 * @param values
	 *            　数据
	 * @return
	 */
	public long lpush(String key, String... values) {
		return this.getJedis().lpush(key, values);
	}

	/**
	 * 把所有数据添加到一个列表中,并且设置列表的存活时间
	 * 
	 * @param key
	 *            列表名
	 * @param values
	 *            数据
	 * @param liveTime
	 *            存活时间--单位(秒)
	 * @return
	 */
	public long lpush(String key, String[] values, int liveTime) {
		long result = this.getJedis().lpush(key, values);
		this.getJedis().expire(key, liveTime);
		return result;
	}

	/**
	 * 删除列表中对应值的元素
	 * 
	 * @param key
	 *            列表名
	 * @param count
	 *            删除多少个相同的元素
	 * @param value
	 *            数据
	 * @return
	 */
	public long lrem(String key, long count, String value) {
		return this.getJedis().lrem(key, count, value);
	}

	/**
	 * 返回指定列表范围内的元素
	 * 
	 * @param key
	 *            列表名
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public List lrange(String key, long start, long end) {
		return this.getJedis().lrange(key, start, end);
	}

	/**
	 * 通过key删除（字节）
	 * 
	 * @param key
	 */
	public void del(byte[] key) {
		this.getJedis().del(key);
	}

	/**
	 * 通过key删除
	 * 
	 * @param key
	 */
	public void del(String key) {
		this.getJedis().del(key);
	}

	/**
	 * 添加key value 并且设置存活时间(byte)
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 *            　单位(秒)
	 */
	public void set(byte[] key, byte[] value, int liveTime) {
		this.set(key, value);
		this.getJedis().expire(key, liveTime);
	}

	/**
	 * 添加key value 并且设置存活时间
	 * 
	 * @param key
	 * @param value
	 * @param liveTime
	 *            　单位(秒)
	 */
	public void set(String key, String value, int liveTime) {
		this.set(key, value);
		this.getJedis().expire(key, liveTime);
	}

	/**
	 * 添加key value
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		this.getJedis().set(key, value);
	}

	/**
	 * 添加key value (字节)(序列化)
	 * 
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value) {
		this.getJedis().set(key, value);
	}

	/**
	 * 获取redis value (String)
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String value = this.getJedis().get(key);
		return value;
	}

	/**
	 * 获取redis value (byte [] )(反序列化)
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key) {
		return this.getJedis().get(key);
	}

	/**
	 * 通过正则匹配keys
	 * 
	 * @param pattern
	 * @return 
	 * @return
	 */
	public Set<String> Setkeys(String pattern) {
		return this.getJedis().keys(pattern);
	}

	/**
	 * 检查key是否已经存在
	 * 
	 * @param key
	 * @return 存在返回true
	 */
	public boolean exists(String key) {
		return this.getJedis().exists(key);
	}

	/**
	 * 清空redis 所有数据
	 * 
	 * @return
	 */
	public String flushDB() {
		return this.getJedis().flushDB();
	}

	/**
	 * 查看redis里有多少数据
	 */
	public long dbSize() {
		return this.getJedis().dbSize();
	}

	/**
	 * 检查是否连接成功
	 * 
	 * @return
	 */
	public String ping() {
		return this.getJedis().ping();
	}

	/**
	 * 通过多个key一次返回多个值
	 * 
	 * @param keys
	 * @return 
	 */
	public List<String> Listmget(String... keys) {
		return this.getJedis().mget(keys);
	}

	/**
	 * 一次添加多个key value到redis中
	 * 
	 * @param keysvalues
	 */
	public void mset(String... keysvalues) {
		this.getJedis().mset(keysvalues);
	}

	/**
	 * 获取一个jedis 客户端
	 * 
	 * @return
	 */
	private Jedis getJedis() {
		if (jedis == null) {
			return jedisConnectionFactory.getShardInfo().createResource();
		}
		return jedis;
	}

	private RedisService() {

	}

	// 操作redis客户端
	private static Jedis jedis;
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
}