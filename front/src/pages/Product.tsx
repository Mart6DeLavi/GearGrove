import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';

interface ProductInfo {
    [key: string]: {
        name: string;
        description: string;
        price: number;
        image: string;
        specs: { [key: string]: string };
        reviews: { author: string; rating: number; comment: string }[];
    };
}

const productInfo: ProductInfo = {
    '1': {
        name: 'RTX 3080 Graphics Card',
        description: 'High-performance graphics card for gaming and content creation.',
        price: 699.99,
        image: '/products/3080.png',
        specs: {
            'CUDA Cores': '8704',
            'Boost Clock': '1.71 GHz',
            'Memory': '10GB GDDR6X',
            'Memory Interface': '320-bit',
        },
        reviews: [
            { author: 'John D.', rating: 5, comment: 'Amazing performance, highly recommended!' },
            { author: 'Sarah M.', rating: 4, comment: 'Great card, but runs a bit hot.' },
        ],
    },
    '2': {
        name: 'Ryzen 9 5950X Processor',
        description: 'Powerful 16-core processor designed for high-performance tasks.',
        price: 749.99,
        image: '/products/r9-5950x.png',
        specs: {
            'Cores': '16',
            'Threads': '32',
            'Base Clock': '3.4 GHz',
            'Max Boost Clock': '4.9 GHz',
            'L3 Cache': '64MB',
        },
        reviews: [
            { author: 'Alex K.', rating: 5, comment: 'Handles multitasking and rendering with ease!' },
            { author: 'Jessica R.', rating: 4, comment: 'Great CPU, though a bit overkill for gaming.' },
        ],
    },
    '3': {
        name: '32GB DDR4 RAM Kit',
        description: 'High-speed DDR4 RAM kit for smooth multitasking and faster load times.',
        price: 159.99,
        image: '/products/ram.png',
        specs: {
            'Memory Size': '32GB',
            'Speed': '3200 MHz',
            'Latency': 'CL16',
            'Voltage': '1.35V',
            'Modules': '2 x 16GB',
        },
        reviews: [
            { author: 'Chris P.', rating: 5, comment: 'Super fast and reliable, great for heavy workloads.' },
            { author: 'Danielle M.', rating: 4, comment: 'Good value for money, though RGB would be a nice touch.' },
        ],
    },
    '4': {
        name: '1TB NVMe SSD',
        description: 'Ultra-fast NVMe SSD for quick load times and efficient data storage.',
        price: 129.99,
        image: '/products/ssd.png',
        specs: {
            'Capacity': '1TB',
            'Read Speed': '3500 MB/s',
            'Write Speed': '3000 MB/s',
            'Interface': 'PCIe Gen 3 x4',
            'Form Factor': 'M.2 2280',
        },
        reviews: [
            { author: 'Samuel L.', rating: 5, comment: 'Extremely fast, reduced boot times significantly!' },
            { author: 'Eva B.', rating: 4, comment: 'Good speed, though it tends to heat up a bit under load.' },
        ],
    },
};

const Product: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const product = id && productInfo[id];

    if (!product) {
        return (
            <div className="flex items-center justify-center min-h-screen bg-gray-100 text-center">
                <h1 className="text-3xl font-bold mb-6">Product Not Found</h1>
                <p>Sorry, we couldn't find the product you're looking for.</p>
            </div>
        );
    }

    return (
        <div className="fixed inset-0 z-50 bg-white overflow-y-auto flex items-center justify-center p-4 md:p-8">
            <button
                onClick={() => navigate(-1)}
                className="absolute top-4 right-4 bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700"
            >
                Close
            </button>

            <div className="bg-white shadow-lg rounded-lg overflow-hidden max-w-4xl w-full">
                <div className="md:flex">
                    <img
                        src={product.image}
                        alt={product.name}
                        className="h-64 w-full object-cover md:w-1/2"
                    />
                    <div className="p-6 md:w-1/2">
                        <h1 className="text-2xl font-bold text-indigo-600 mb-2">{product.name}</h1>
                        <p className="text-gray-700 mb-4">{product.description}</p>
                        <p className="text-3xl font-bold text-gray-900 mb-4">${product.price.toFixed(2)}</p>
                        <button className="w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700 transition duration-300">
                            Add to Cart
                        </button>
                    </div>
                </div>

                <div className="border-t border-gray-200 p-6">
                    <h2 className="text-xl font-bold mb-4">Specifications</h2>
                    <dl className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                        {Object.entries(product.specs).map(([key, value]) => (
                            <div key={key} className="flex justify-between">
                                <dt className="font-medium text-gray-600">{key}</dt>
                                <dd className="text-gray-900">{value}</dd>
                            </div>
                        ))}
                    </dl>
                </div>

                <div className="border-t border-gray-200 p-6">
                    <h2 className="text-xl font-bold mb-4">Customer Reviews</h2>
                    {product.reviews.map((review, index) => (
                        <div key={index} className="border-b border-gray-200 pb-4 mb-4 last:border-0 last:pb-0">
                            <div className="flex justify-between items-center">
                                <span className="font-semibold">{review.author}</span>
                                <span className="text-yellow-500">
                                    {'★'.repeat(review.rating)}{'☆'.repeat(5 - review.rating)}
                                </span>
                            </div>
                            <p className="text-gray-600">{review.comment}</p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Product;
