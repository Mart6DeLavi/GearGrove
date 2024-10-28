import {UserRegistrationDto} from "../dtos/UserRegistrationDto";

export const createUser = async (user : UserRegistrationDto) => {
    try {
        const response = await fetch('/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            throw new Error('Ошибка регистрации');
        }

        // Обработка успешного ответа
        const data = await response.json();
        console.log('Пользователь успешно зарегистрирован:', data);
    } catch (error) {
        console.error('Ошибка:', error);
    }
}