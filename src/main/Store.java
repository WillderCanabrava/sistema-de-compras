package main;

import model.Product;
import util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Store {

    private static Scanner scan = new Scanner(System.in);
    private static ArrayList<Product> products;
    private static Map<Product, Integer> shop;

    public static void main(String[] args) {
        products = new ArrayList<>();
        shop = new HashMap<>();
        menu();
    }

    private static void menu() {

        System.out.println("-----------------------------------------------");
        System.out.println("-------Bem - Vindo(a) à Canabrava Store!-------");
        System.out.println("-----------------------------------------------");
        System.out.println("****Selecione uma opção que deseja realizar****");
        System.out.println("-----------------------------------------------");
        System.out.println("|    Opção 1 - Cadastrar    |");
        System.out.println("|    Opção 2 - Listar       |");
        System.out.println("|    Opção 3 - Comprar      |");
        System.out.println("|    Opção 4 - Carrinho     |");
        System.out.println("|    Opção 5 - Sair         |");

        int option = scan.nextInt();

        switch (option) {
            case 1:
                registerProducts();
                break;
            case 2:
                listProducts();
                break;
            case 3:
                buyProducts();
                break;
            case 4:
                viewShop();
                break;
            case 5:
                System.out.println("Obrigado! Volte sempre!");
                System.exit(0);
            default:
                System.out.println("Por favor, digite uma opção válida.");
                menu();
                break;
        }
    }

    private static void registerProducts() {
        System.out.println("Informe o produto: ");
        String productName = scan.next();

        System.out.println("Informe o preço: ");
        Double priceProduct = scan.nextDouble();

        Product product = new Product(productName, priceProduct);
        products.add(product);

        System.out.println(product.getName() + " cadastrado com sucesso!");
        menu();
    }

    private static void listProducts() {
        if (products.size() > 0) {
            System.out.println("Lista de produtos: \n");

            for (Product p : products) {
            System.out.println(p);
            }
        } else {
            System.out.println("Não há produtos cadastrados!");
        }
        menu();
    }

    private static void buyProducts() {
        if (products.size() > 0) {
            System.out.println("Código do produto: \n");

            System.out.println("----------Produtos disponíveis----------");

            for (Product p : products) {
                System.out.println(p + "\n");
            }

            int id = Integer.parseInt(scan.next());
            boolean isPresent = false;

            for (Product p : products) {
                if (p.getId() == id) {
                    int qtt = 0;
                    try {
                        qtt = shop.get(p);
                        shop.put(p, qtt + 1);
                    }
                    catch (NullPointerException e) {
                        shop.put(p, 1);
                    }

                    System.out.println(p.getName() + " adicionado ao carrinho!");
                    isPresent = true;

                    if (isPresent) {

                        System.out.println("Deseja adicionar outro produto?");
                        System.out.println("Digite 1 para 'sim' ou 0 para finalizar a compra.\n");
                        int option = Integer.parseInt(scan.next());

                        if (option == 1) {
                            buyProducts();
                        } else {
                            finishShop();
                        }
                    }
                } else {
                    System.out.println("Produto não encontrado.");
                    menu();
                }
            }
        } else {
            System.out.println("Não há produtos cadastrados.");
            menu();
        }

    }

    private static void viewShop() {
        System.out.println("-----Produtos no seu carrinho-----");

        if (shop.size() > 0) {
            for (Product p : shop.keySet()) {
                System.out.println("Produto: " + p + "\nQuantidade: " + shop.get(p));
            }
        } else {
            System.out.println("Seu carrinho está vazio.");
        }
        menu();
    }

    private static void finishShop() {
        Double shopValue = 0.0;
        System.out.println("Seus produtos: ");

        for (Product p : shop.keySet()) {
            int qtt = shop.get(p);
            shopValue += p.getPrice() * qtt;
            System.out.println(p);
            System.out.println("Quantidade: " + qtt);
        }

        System.out.println("O valor da sua compra é: " + Util.doubleToString(shopValue));
        shop.clear();
        System.out.println("Obrigado por comprar na Canabrava Store!");
        menu();


    }

}
