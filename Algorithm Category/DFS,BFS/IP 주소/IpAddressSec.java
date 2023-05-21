import java.util.*;

class IpAddressSec {
    int len;
    LinkedList<String> temp;
    ArrayList<String> result;

    public String[] solution(String s) {
        len = s.length();
        temp = new LinkedList<>();
        result = new ArrayList<>();

        DFS(0, s);

        return result.toArray(new String[0]);
    }

    public void DFS(int start, String input) {

        if(temp.size() == 4) {
            // 0~255 ����, 0���� �������� �ʾƼ� ���ڿ� input�� ���� ���� ��ȸ���� �ʾƵ� size�� 4�� ������ �� �ִ�.
            // �ٸ� input�� �� ��ȸ�ߴٴ� �ǹ̷� start�� len�� �����ؾ��� result�� �߰��� �� �ִ�.
            if(start == len) {
                result.add(String.join(".", temp));
            }

        } 
        
        else {
            // 2, 20, 202 �̷��� ó������ 3�� �����̰��� �Ѵ�.
            for(int i = 1; i <= 3; i++) {

                // subString�� indexOutOFBound ����
                // if�� ���� �ȿ����� start + i�� len�� ������ ���� ������, start + i�� len���� Ŀ�� ���� ����.
                if(start + i > len) {
                    break;
                }
                String segment = input.substring(start, start + i);

                // 0���� �����ϴ� 2�ڸ� �̻� ���� ���� || 3�ڸ� ���ڷ� �̷������ 255 �ʰ� ���� ����
                //��ȿ���� ���� ���(��: 255���� ũ�ų� 0���� �����ϴ� ���� �ڸ� ����) ������ ��ġ���� �����ϴ� �� �� ���׸�Ʈ�� Ȯ���� ������ ����. (break ����)
                // �������� �� �� ���׸�Ʈ�� ��ȿ���� �ʴٴ� ���� �̹� �˰� �ֱ� ������ continue�� ��� �����Դϴ�.
                if((segment.startsWith("0") && segment.length() > 1) || (Integer.parseInt(segment) > 255)) {
                    // continue ��� �ǹ̴� ���� level���� ���� ����� ���� �����ϴ� �ǹ��̰�
                    // continue�� ���ؼ� for���� �������� ������ ���, ���� level���� ���� ���� �Ѿ��.
                    break;
                }

                temp.add(segment);
                DFS(start + i, input);
                temp.pollLast();
            }
        }
    }


	public static void main(String[] args){
		IpAddressSec T = new IpAddressSec();
		System.out.println(Arrays.toString(T.solution("2025505")));	
		System.out.println(Arrays.toString(T.solution("0000")));
		System.out.println(Arrays.toString(T.solution("255003")));
		System.out.println(Arrays.toString(T.solution("155032012")));
		System.out.println(Arrays.toString(T.solution("02325123")));
		System.out.println(Arrays.toString(T.solution("121431211")));
	}
}