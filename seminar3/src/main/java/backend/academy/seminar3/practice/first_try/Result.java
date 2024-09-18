package backend.academy.seminar3.practice.first_try;

/**
 * Необходимо реализовать специальную сущность Result,
 * результат какой-либо операции, исход которой может быть классифицирован, как:
 *
 * * Ошибка: Операция завершена с ошибкой. Текст ошибки инкапсулирован в сущность результата.
 * * Успех: Операция успешно завершена и её результат инкапсулирован в сущность результата.
 *
 * Мы хотим удобно работать с результатом и уметь однозначно определять, какой именно тип перед нами (ошибка или успех).
 *
 * Мы не хотим писать много boilerplate кода.
 */
public sealed class Result<T> {

    public boolean isSuccess() {
        return switch (this) {
            case Success success -> true;
            case Failure failure -> false;
        }
    }

    public String getResultStringOrError() {
        return switch (this) {
            case Success success(T result) -> "Success: " + result.toString();
            case Failure failure(String message) -> "Failure: " + message;
        }
    }

    public T getResult() {
        return switch (this) {
            case Success success(T result) -> result;
            case Failure failure -> throw new IllegalStateException("Result is failure");
        }
    }

    public String getErrorMessage() {
        return switch (this) {
            case Success success -> throw new IllegalStateException("Result is success");;
            case Failure failure(String message) -> message;
        }
    }

    public Result<T> onSuccess(Consumer<T> onSuccess) {
        switch (this) {
            case Success success(T result) -> onSuccess.accept(result);
            case Failure failure -> return;
        }
        return this;
    }
}

public record Success<T>(T result) implements Result<T> { }

public record Failure<T>(String message) implements Result<T> { }
