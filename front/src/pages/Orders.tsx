import React from 'react';
import styles from './Orders.module.css';

interface Order {
    id: string;
    date: string;
    total: number;
    status: 'Processing' | 'Shipped' | 'Delivered';
    items: {
        name: string;
        quantity: number;
        price: number;
    }[];
}

const orders: Order[] = [
    {
        id: 'ORD-001',
        date: '2023-05-15',
        total: 879.98,
        status: 'Processing',
        items: [
            { name: 'RTX 3080 Graphics Card', quantity: 1, price: 699.99 },
            { name: '32GB DDR4 RAM Kit', quantity: 1, price: 159.99 },
        ],
    },
    {
        id: 'ORD-002',
        date: '2023-04-30',
        total: 879.98,
        status: 'Shipped',
        items: [
            { name: 'Ryzen 9 5950X Processor', quantity: 1, price: 749.99 },
            { name: '1TB NVMe SSD', quantity: 1, price: 129.99 },
        ],
    },
    {
        id: 'ORD-003',
        date: '2023-04-15',
        total: 459.97,
        status: 'Delivered',
        items: [
            { name: '27" 4K Monitor', quantity: 1, price: 399.99 },
            { name: 'Mechanical Keyboard', quantity: 1, price: 59.98 },
        ],
    },
];

const Orders: React.FC = () => {
    return (
        <div className={styles.container}>
            <h1 className={styles.title}>Your Orders</h1>
            {orders.map((order) => (
                <div key={order.id} className={styles.orderCard}>
                    <div className={styles.orderHeader}>
                        <div className={styles.orderInfo}>
                            <h2 className={styles.orderId}>Order {order.id}</h2>
                            <p className={styles.orderDate}>Placed on {order.date}</p>
                        </div>
                        <div>
                            <p className={styles.orderTotal}>${order.total.toFixed(2)}</p>
                            <p className={`${styles.orderStatus} ${getStatusClass(order.status)}`}>{order.status}</p>
                        </div>
                    </div>
                    <div className={styles.orderContent}>
                        <table className={styles.orderTable}>
                            <thead>
                            <tr className={styles.tableHeader}>
                                <th className={styles.tableHeaderCell}>Item</th>
                                <th className={styles.tableHeaderCell}>Quantity</th>
                                <th className={styles.tableHeaderCell}>Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            {order.items.map((item, index) => (
                                <tr key={index} className={styles.tableRow}>
                                    <td className={styles.tableCell}>{item.name}</td>
                                    <td className={styles.tableCell}>{item.quantity}</td>
                                    <td className={styles.tableCell}>${item.price.toFixed(2)}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            ))}
        </div>
    );
};

const getStatusClass = (status: Order['status']) => {
    switch (status) {
        case 'Processing':
            return styles.statusProcessing;
        case 'Shipped':
            return styles.statusShipped;
        case 'Delivered':
            return styles.statusDelivered;
        default:
            return '';
    }
};

export default Orders;