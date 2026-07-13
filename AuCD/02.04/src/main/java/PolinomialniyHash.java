import java.util.ArrayList;
import java.util.List;

//hash(a)=(a[0]⋅q^M−1 +a[1]⋅q^M−2 +...+a[M−2]⋅q+a[M−1])%P
//h = q^M-1 % P
//hashNew(a)= (q(hashOld-a[0]*h) + a[M])%P
//q - основание (31, так как латинские буквы) P - модуль, M - длина подстроки

public class PolinomialniyHash {
    public static void main(String[] args) {
        String str = "abbaBBghabbafdnhdabbavdabba";
        String substr = "abba";
        int q = 31;
        long P = (long) Math.pow(10, 9) + 7;

        List<int[]> res = findIndex(str, substr, q, P);
        for (int[] r : res) {
            System.out.println(r[0] + " - " + r[1]);
        }
    }

    public static long HashCalculation(String str, int q, long P) { //вычисление hash по первой формуле
        long hash =  0;
        for (int i = 0; i < str.length(); i++) {
            hash = (str.charAt(i) + hash * q) % P;
        }
        return hash;
    }

    public static long HCalculation (String substr, int q, long P) {
        int M = substr.length();
        long h = 1;
        for (int i = 0; i < M - 1; i++) {
            h = (h * q) % P;
        }
        return h;
    } //h нужен для того, чтобы быстро удалить вклад первого символа при сдвиге окна

    public static List<int[]> findIndex(String str, String substr, int q, long P) { //поиск всех вхождений подстроки
        List<int[]> result = new ArrayList<>();
        int M = substr.length();
        if (M > str.length()) return result;
        long targetHash = HashCalculation(substr, q, P); //хэш искомой подстроки
        long h = HCalculation(substr, q, P); //коэффициент q^M-1 для удаления первого символа
        long currentHash = HashCalculation(str.substring(0, M), q, P); //вычисление первого хэша длиной M
        if (currentHash == targetHash) {
            result.add(new int[]{0, M - 1});
        }
        for (int i = 0; i < str.length() - M; i++) { //i - индекс символа, который выходит из окна
            long removedPart = (str.charAt(i) * h) % P; //убираем старый первый символ, чтобы стереть его мы должны вычислить, сколько весил этот символ в общей сумме хэша (h = q^M-1)
            long tempHash = (currentHash - removedPart + P) % P; //вычитаем вклад ушедшего символа из общего хэша (P добавляем, чтобы избежать орицательных значений остатка)

            currentHash = (tempHash * q + str.charAt(i + M)) % P; //умножаем оставшиеся сиволы на q(сдвигаем степени) + добавляем новый символ, который входит в окно (позиция i+M4 str.charAt(i+M))

            int startIndex = i + 1; //вычисляем индекс текущей подстроки
            int endIndex = startIndex + M - 1;

            if (currentHash == targetHash) {
                result.add(new int[]{startIndex, endIndex});
            }
        }
        return result;
    }
}
