import {UserLoginDto} from "../dtos/UserLoginDto";

export const loginUser = async (user : UserLoginDto) => {
    try {
        const response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            throw new Error('Ошибка авторизации');
        }

        const data = await response.json();
        console.log('Пользователь успешно авторизирован: ', data);
    } catch (error) {
        console.error('Error: ', error);
    }
}