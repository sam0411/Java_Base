##Manual of Alex
1.Redis
2.Memcached


##Redis
redis-server  redis.windows.conf

##String
get
set 
del

##Linked-list
lpush
rpush
lpop
rpop
lindex
lrange

##Set
sadd
srem
smembers
sismember
sinter
sunion
sdiff

##Hash
hset
hget
hdel
hgetall

##Zset
zadd
zrem
zrange
zrangebyscore


###Memcached
memcached -d start
memcached -d stop

telnet 127.0.0.1 11211