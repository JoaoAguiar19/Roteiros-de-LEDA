package orderStatistic;

import org.junit.Test;

public class OrderStatisticsHeapImplTest {
    @Test
    public void testGetOrderStatistics() {
        OrderStatisticsHeapImpl<Integer> orderStatistics = new OrderStatisticsHeapImpl<>();

        Integer[] array = {7, 10, 4, 3, 20, 15};

        // Teste para k = 1 (menor elemento)
        assert orderStatistics.getOrderStatistics(array, 1).equals(3);

        // Teste para k = 3 (terceiro menor elemento)
        assert orderStatistics.getOrderStatistics(array, 3).equals(7);

        // Teste para k = 6 (maior elemento)
        assert orderStatistics.getOrderStatistics(array, 6).equals(20);

        // Teste para k fora dos limites (0 e 7)
        assert orderStatistics.getOrderStatistics(array, 0) == null;
        assert orderStatistics.getOrderStatistics(array, 7) == null;

        // Teste para array nulo
        assert orderStatistics.getOrderStatistics(null, 3) == null;

        // Teste para array vazio
        assert orderStatistics.getOrderStatistics(new Integer[]{}, 1) == null;
    }
}
