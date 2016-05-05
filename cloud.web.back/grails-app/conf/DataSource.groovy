dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://127.0.0.1:3306/cloud_web?characterEncoding=utf8"
            properties {
                username = "root"
                password = "root"
                maxIdle = "30"
                maxWait = "10000"
                maxActive = -1
            }
        }
    }
    test {
        dataSource {
            jndiName = "java:comp/env/jdbc/sdk"
        }
    }
    production {
        dataSource {
            jndiName = "java:comp/env/jdbc/sdk"
        }
    }
}
