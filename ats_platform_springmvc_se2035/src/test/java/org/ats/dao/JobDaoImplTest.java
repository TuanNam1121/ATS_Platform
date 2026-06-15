package org.ats.dao;

import org.ats.config.WebDataConfig;
import org.ats.entities.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = WebDataConfig.class)
class JobDaoImplTest {

        @Autowired
        private SessionFactory sessionFactory;

        @Test
        @DisplayName("Test Hibernate Level 2 Cache với Caffeine")
        void testHibernateL2Cache() {
            // 1. Bật thống kê để theo dõi
            sessionFactory.getStatistics().setStatisticsEnabled(true);
            // Xóa sạch cache trước khi test để đảm bảo kết quả chính xác
            sessionFactory.getCache().evictAllRegions();

            // Lần 1: Lấy dữ liệu từ Database (Sẽ là một Cache Miss)
            long start1 = System.currentTimeMillis();
            try (Session s1 = sessionFactory.openSession()) {
                s1.find(Job.class, 1L);
            }
            long duration1 = System.currentTimeMillis() - start1;

            // Xóa sạch buffer của DB để đảm bảo lần 2 không đọc từ DB cache
            try (Session s = sessionFactory.openSession()) {
                s.doWork(connection -> connection.createStatement().execute("DBCC DROPCLEANBUFFERS"));
            } catch (Exception ignored) {}

            // Lần 2: Lấy dữ liệu lần nữa (Hy vọng là Cache Hit)
            long start2 = System.currentTimeMillis();
            try (Session s2 = sessionFactory.openSession()) {
                s2.find(Job.class, 1L);
            }
            long duration2 = System.currentTimeMillis() - start2;

            long hits = sessionFactory.getStatistics().getSecondLevelCacheHitCount();

            System.out.println("\n--- KẾT QUẢ TEST L2 CACHE ---");
            System.out.println("⏱️ Lần 1 (DB): " + duration1 + " ms");
            System.out.println("⏱️ Lần 2 (Cache): " + duration2 + " ms");
            System.out.println("📊 Hits: " + hits);
            System.out.println(hits > 0 ? "✅ L2 Cache hoạt động tốt!" : "❌ L2 Cache chưa hoạt động!");
            System.out.println("-----------------------------\n");
        }
}