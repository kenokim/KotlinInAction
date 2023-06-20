## Chapter07. 연산자 오버로딩과 기타 관례

자바에는 표준 라이브러리와 연관된 언어 기능이 있다.

for ... in loop 에서 Iterable 을 구현한 객체를 사용할 수 있다.

try-with resources 에서 AutoCloseable 을 구현한 객체를 사용할 수 있다.

```java
    public static void main(String[] args) {
        JavaCollection javaCollection = new JavaCollection();
        for (int a : javaCollection) {
            
        }
        
        try (JavaResource resource = new JavaResource()) {
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    static class JavaCollection implements Iterable<Integer> {
        @NotNull
        @Override
        public Iterator iterator() {
            return null;
        }
    }
    
    static class JavaResource implements AutoCloseable {
        @Override
        public void close() throws Exception {
            
        }
    }
```
