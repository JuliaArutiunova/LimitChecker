package com.example.limit_checker.controller;

import com.example.limit_checker.dto.LimitCreateDto;
import com.example.limit_checker.dto.LimitInfoDto;
import com.example.limit_checker.dto.TransactionInfoDto;
import com.example.limit_checker.dto.error.ErrorResponseDto;
import com.example.limit_checker.service.api.ILimitService;
import com.example.limit_checker.service.api.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User API",
        description = "Client-facing API for managing user account limits and retrieving transaction information.")
public class UserController {

    private final ITransactionService transactionService;
    private final ILimitService limitService;

    public UserController(ITransactionService transactionService, ILimitService limitService) {
        this.transactionService = transactionService;
        this.limitService = limitService;
    }

    @GetMapping("/{account}/limits")
    @Operation(
            summary = "Get Limits",
            description =
                    "Retrieves the list of limits for a specific user account. Optionally filters by expense category.",
            parameters = {
                    @Parameter(name = "account",
                            in = ParameterIn.PATH, description = "Account identifier.",
                            required = true, schema = @Schema(type = "string", example = "0001234567")),
                    @Parameter(name = "expenseCategory ",
                            in = ParameterIn.QUERY, description = "Optional filter by expense category.",
                            required = false, schema = @Schema(type = "string", example = "product"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved account limits.",
                            content = @Content(array = @ArraySchema(
                                    schema = @Schema(implementation = LimitInfoDto.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    public List<LimitInfoDto> getLimits(@PathVariable("account") String account,
                                        @RequestParam(value = "expenseCategory ", required = false)
                                        String expenseCategory) {
        if (expenseCategory != null && !expenseCategory.isBlank()) {
            return limitService.getAllByExpenseCategory(account, expenseCategory);
        } else {
            return limitService.getAll(account);
        }
    }

    @PostMapping("/{account}/limits")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Set New Limit",
            description = "Sets a new limit for a specific user account and expense category.",
            parameters = {
                    @Parameter(name = "account", in = ParameterIn.PATH, description = "Account identifier.",
                            required = true, schema = @Schema(type = "string", example = "0001234567"))
            },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Limit successfully created."),
                    @ApiResponse(responseCode = "400", description = "Invalid limit data provided.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    public void createLimit(@PathVariable("account") String account,
                            @RequestBody @Valid LimitCreateDto limitCreateDto) {
        limitService.create(account, limitCreateDto);
    }

    @GetMapping("/{account}/transactions/exceeded-limit")
    @Operation(
            summary = "Get Transactions Exceeding Limit",
            description =
                    "Retrieves a list of transactions for a specific user account that exceeded their spending limit. " +
                            "Optionally filters by expense category.",
            parameters = {
                    @Parameter(name = "account",
                            in = ParameterIn.PATH, description = "Account identifier.",
                            required = true, schema = @Schema(type = "string", example = "0001234567")),
                    @Parameter(name = "expenseCategory ",
                            in = ParameterIn.QUERY, description = "Optional filter by expense category.",
                            required = false, schema = @Schema(type = "string", example = "Shopping"))
            },
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successfully retrieved transactions exceeding the limit.",
                            content = @Content(array = @ArraySchema(schema
                                    = @Schema(implementation = TransactionInfoDto.class)))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponseDto.class)))
            }
    )
    public List<TransactionInfoDto> get(@PathVariable("account") String account,
                                        @RequestParam(value = "expenseCategory ", required = false)
                                        String expenseCategory) {
        if (expenseCategory != null && !expenseCategory.isBlank()) {
            return transactionService.getAllExceededLimitByExpenseCategory(account, expenseCategory);
        } else {
            return transactionService.getAllExceededLimit(account);
        }
    }


}
