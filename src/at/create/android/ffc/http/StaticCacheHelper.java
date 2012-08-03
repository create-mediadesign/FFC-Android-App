package at.create.android.ffc.http;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class StaticCacheHelper {
    private static final int TIME_TO_LIVE = 43200000; // 12 hours

    private static Map<String, Element> cacheMap = new HashMap<String, Element>();

    /**
     * Retrieves an item from the cache. If found, the method compares
     * the object's expiration date to the current time and only returns
     * the object if the expiration date has not passed.
     * 
     * @param cacheKey
     * @return
     */
    public static Object retrieveObjectFromCache(String cacheKey) {
        Element e = cacheMap.get(cacheKey);
        Object o = null;
        if (e != null) {
            Date now = new Date();
            if (e.getExpirationDate().after(now)) {
                o = e.getObject();
            } else {
                removeCacheItem(cacheKey);
            }
        }
        return o;
    }

    /**
     * Stores an object in the cache, wrapped by an Element object.
     * The Element object has an expiration date, which will be set to 
     * now + this class' TIME_TO_LIVE setting.
     * 
     * @param cacheKey
     * @param object
     */
    public static void storeObjectInCache(String cacheKey, Object object) {
        Date expirationDate = new Date(System.currentTimeMillis() + TIME_TO_LIVE);
        Element e = new Element(object, expirationDate);
        cacheMap.put(cacheKey, e);
    }

    /**
     * Stores an object in the cache, wrapped by an Element object.
     * The Element object has an expiration date, which will be set to 
     * now + the timeToLiveInMilliseconds value that is passed into the method.
     * 
     * @param cacheKey
     * @param object
     * @param timeToLiveInMilliseconds
     */
    public static void storeObjectInCache(String cacheKey, Object object, int timeToLiveInMilliseconds) {
        Date expirationDate = new Date(System.currentTimeMillis() + timeToLiveInMilliseconds);
        Element e = new Element(object, expirationDate);
        cacheMap.put(cacheKey, e);
    }

    public static void removeCacheItem(String cacheKey) {
        cacheMap.remove(cacheKey);
    }

    public static void clearCache() {
        cacheMap.clear();
    }
    
    static class Element {

        private Object object;
        private Date expirationDate;

        /**
         * @param object
         * @param key
         * @param expirationDate
         */
        private Element(Object object, Date expirationDate) {
            super();
            this.object = object;
            this.expirationDate = expirationDate;
        }
        /**
         * @return the object
         */
        public Object getObject() {
            return object;
        }
        /**
         * @param object the object to set
         */
        public void setObject(Object object) {
            this.object = object;
        }
        /**
         * @return the expirationDate
         */
        public Date getExpirationDate() {
            return expirationDate;
        }
        /**
         * @param expirationDate the expirationDate to set
         */
        public void setExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
        }
    }
}
